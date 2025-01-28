import mysql.connector
import datetime as dt
import random as rd
from time import *

# Replace with your actual database credentials
host = "localhost" 
user = "root"
password = "admin123456"
database = "auctions"

import re
from unidecode import unidecode

def generate_random_vietnamese_name(n=10000):

    # Define lists of common Vietnamese surnames and given names
    surnames = ["Nguyễn", "Trần", "Lê", "Phạm", "Vũ", "Võ", "Hoàng", "Đặng", "Bùi", "Hồ"]
    male_given_names = ["Hoàng", "Minh", "Anh", "Dũng", "Tuấn", "Khánh", "Việt", "Trung", "Hiếu", "Nam"]
    female_given_names = ["Hương", "Lan", "Hồng", "Mai", "Linh", "Thảo", "Ngọc", "Thanh", "Diệu"]
    male_second_names = ["Văn", "Trung", "Mạnh", "Hữu"]
    female_second_names = ["Thị", "Thu", "Hà"]

    names = []
    for _ in range(n):
        # Randomly select surname
        surname = rd.choice(surnames)

        # Determine gender (simplified for demonstration)
        is_male = rd.choice([True, False]) 

        # Select given name based on gender
        given_name = rd.choice(male_given_names) if is_male else rd.choice(female_given_names)

        second_name = rd.choice(male_second_names) if is_male else rd.choice(female_second_names)

        # Combine surname and given name
        name = surname + " " + second_name + " " + given_name
        names.append(name)

    return names

def convert_to_ascii(vietnamese_name):

    return unidecode(vietnamese_name)

def convert_name(full_name):

    # 1. Convert to lowercase
    lower_name = full_name.lower()

    # 2. Remove diacritics (optional)
    # You can use a library like unidecode for this
    # lower_name = unidecode(lower_name) 

    # 3. Extract the last name component
    last_name = re.findall(r'\b[a-z]+\b$', lower_name)[0] 


    # 5. Combine with the first letter of other names
    first_letters = ''.join([name[0] for name in lower_name.split()[:-1]])

    # 6. Concatenate and return
    return last_name + first_letters

def get_number(st):
    return re.findall(r'\d+', st)

def update_bidder():
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor()

    # Execute a SQL query (example: SELECT query)
    query = "SELECT * FROM bidder;"  # Replace with your actual query
    cursor.execute(query)

    # Fetch all rows
    rows = cursor.fetchall()

    # Print the results
    names = generate_random_vietnamese_name(10000)
    for idx in range(1, 10000):
        if idx % 1000 == 0:
            print(idx)
        id = idx
        email = convert_name(convert_to_ascii(names[idx])) + str(rd.randint(1000, 9999)) + "@gmail.com"
        username = convert_name(convert_to_ascii(names[idx])) + str(rd.randint(1000, 9999))
        name = names[idx]

        cash = rd.randint(1, 20000)
        created_date = dt.datetime(rd.randint(2020, 2024), rd.randint(1, 12), rd.randint(1, 28)) + dt.timedelta(seconds=rd.randint(1, 80000))
        query = f"INSERT INTO bidder VALUES ('{id}', '{name}', '{email}', '{cash}', '{created_date}', '{username}');"
        try:
            cursor.execute(query)
        except Exception as e:
            print(e)
    conn.commit()
    
    rows = cursor.fetchall()
    for row in rows[:30]:
        print(row)

def update_auctioneer():
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor()
    names = generate_random_vietnamese_name(10000)
    for id in range(1, 10000):
        if id % 1000 == 0:
            print("Created " + str(id) + " auctioneers")
        try:
            name = names[id]
            username = convert_name(convert_to_ascii(name)) + str(rd.randint(1000, 9999))
            email = username + "@gmail.com"
            cash = rd.randint(10000, 20000)
            created_date = dt.datetime(rd.randint(2020, 2024), rd.randint(1, 12), rd.randint(1, 28)) + dt.timedelta(seconds=rd.randint(1, 80000))
            query = f"INSERT INTO auctioneer VALUES ('{id}', '{name}', '{email}', '{cash}', '{created_date}', '{username}');"
            cursor.execute(query)
        except Exception as e:
            print(e)
    conn.commit()

def update_product():
    
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor()
    auctioneer_id = 1
    for id in range(1, 10000):
        if id % 1000 == 0:
            print("Created " + str(id) + " products")
        try:
            auctioneer_id += rd.randint(0, rd.randint(0, 1))
            name = "Product " + str(id)
            image_url = "image/products/p_" + str(id) + ".png"
            beginning_price = rd.randint(100, 10000)
            current_price = beginning_price + rd.randint(100, 10000)
            created_date = dt.datetime(rd.randint(2020, 2024), rd.randint(1, 12), rd.randint(1, 28)) + dt.timedelta(seconds=rd.randint(1, 80000))
            state = rd.choice([0, 1])   
            query = f"INSERT INTO product VALUES ('{id}', '{auctioneer_id}', '{name}', '{image_url}', '{beginning_price}', '{current_price}', '{created_date}', '{state}');"
            cursor.execute(query)
        except Exception as e:
            print(e)
    conn.commit()

def update_bidden_price():
    
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor()

    query = "SELECT id FROM product;"
    cursor.execute(query)
    rows = cursor.fetchall()
    product_ids = [row[0] for row in rows]

    query = "SELECT id FROM bidder;"
    cursor.execute(query)
    rows = cursor.fetchall()
    bidder_ids = [row[0] for row in rows]

    subset_bidder_ids = rd.sample(bidder_ids, 4)
    count_id = 0
    for idx in range(len(product_ids)):
        if idx % 100 == 0:
            print("Created " + str(idx) + " bidden prices")
        product_id = product_ids[idx]
        subset_bidder_ids = rd.sample(bidder_ids, rd.randint(3, 10))
        n_bids = rd.randint(10, 50)
        previous_bidder_id = -1
        previous_price = -1
        previous_datetime = dt.datetime(rd.randint(2020, 2024), rd.randint(1, 12), rd.randint(1, 28)) + dt.timedelta(seconds=rd.randint(1, 80000))
        for _ in range(n_bids):
            while True:
                bidder_id = rd.choice(subset_bidder_ids)
                if bidder_id != previous_bidder_id:
                    break
            count_id += 1
            price = previous_price + rd.randint(100, 1000)
            created_date = previous_datetime + dt.timedelta(seconds=rd.randint(1000, 8000))
            query = f"INSERT INTO bidden_price VALUES ('{count_id}', '{bidder_id}', '{product_id}', '{price}', '{created_date}');"
            try:
                cursor.execute(query)
            except Exception as e:
                print(e)
            previous_bidder_id = bidder_id
            previous_datetime = created_date
            previous_price = price
        conn.commit()

if __name__ == "__main__":
    start = time()
    # update_bidder()
    # update_auctioneer()
    # update_product()
    update_bidden_price()

    print("Time:", time() - start)