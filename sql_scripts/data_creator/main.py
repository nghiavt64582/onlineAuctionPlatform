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

def convert_name_to_short_form(full_name):

    # Vũ Trung Nghĩa => nghiavt
    # Trần Quang Đạt => dattq

    # 1. Convert to lowercase
    lower_name = full_name.lower()

    # 2. Remove diacritics (optional)
    # You can use a library like unidecode for this
    lower_name = unidecode(lower_name) 

    # 3. Extract the last name component
    last_name = re.findall(r'\b\w+\b$', lower_name, re.UNICODE)[0] 


    # 5. Combine with the first letter of other names
    first_letters = ''.join([name[0] for name in lower_name.split()[:-1]])

    # 6. Concatenate and return
    return last_name + first_letters

def convert_name_to_random_email(full_name):
    # Vũ Trung Nghĩa => nghiavt42729@gmail.com
    # Lê Thị Huyền => huyenlt59283@gmail.com
    return convert_name_to_short_form(full_name) + str(rd.randint(10000, 99999)) + "@gmail.com"

def get_random_location():
    LOCATIONS = [
        "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh",
        "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau",
        "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk", "Đắk Nông", "Điện Biên",
        "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội",
        "Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên",
        "Khánh Hòa", "Kiên Giang", "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn",
        "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận",
        "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh",
        "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh", "Thái Bình", "Thái Nguyên",
        "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "TP. Hồ Chí Minh", "Trà Vinh", "Tuyên Quang",
        "Vĩnh Long", "Vĩnh Phúc", "Yên Bái"
    ]
    weights = [10 if location in ["Hà Nội", "TP. Hồ Chí Minh"] else 1 for location in LOCATIONS]
    return rd.choices(LOCATIONS, weights=weights, k = 1)[0]

def get_random_state():
    STATES = ["PENDING", "AUCTIONING", "SOLD"]
    return rd.choice(STATES)

def get_random_cash():
    return rd.randint(1000, 10000)

def get_random_date():
    return dt.datetime(rd.randint(2020, 2024), rd.randint(1, 12), rd.randint(1, 28)) + dt.timedelta(seconds=rd.randint(1, 80000))

def get_number(st):
    return re.findall(r'\d+', st)

def get_random_categories():
    global N_CATE
    categories = []
    VOWEL = "aueio"
    CONSONANT = "mnprtlkhgdsvb"
    LAST = "mntpc"
    def get_random_key():
        return rd.choice(CONSONANT) + rd.choice(VOWEL) + rd.choice(LAST)
    
    for i in range(N_CATE):
        id = i
        first_key = get_random_key()
        second_key = get_random_key()
        categories.append(f"cate_{id}_{first_key}_{second_key}")
    return categories

def init_connection():
    global conn, cursor
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor()

def get_random_role():
    ROLES = ["AUCTIONEER", "BIDDER", "ADMIN"]
    probabilities = [0.1, 0.89, 0.01]  # Probabilities must sum up to 1

    return rd.choices(ROLES, weights = probabilities, k=1)[0]

def create_random_users():
    global users, bidders, auctioneers, N_USERS
    users = []
    bidders = []
    auctioneers = []
    names = generate_random_vietnamese_name(N_USERS)
    mark_username = set()
    for i in range(N_USERS):
        id = i + 1
        name = names[i]
        username = convert_name_to_short_form(name) + str(rd.randint(1000, 9999))
        if username in mark_username:
            continue
        mark_username.add(username)
        password = "{noop}123456"
        enabled = 1
        email = convert_name_to_random_email(name)
        created_date = get_random_date()
        last_login = created_date
        role = get_random_role()
        user = {
            "id": id,
            "name": name,
            "username": username,
            "password": password,
            "enabled": enabled,
            "email": email,
            "created_date": created_date,
            "last_login": last_login,
            "role": role
        }
        users.append(user)
        if role == "BIDDER":
            bidders.append(user)
        if role == "AUCTIONEER":
            auctioneers.append(user)
    print("Finish create user")

def create_insert_bidder_query():
    global bidders
    file = open("sql_scripts/insert_bidder_queries.sql", "w", encoding = 'utf-8')

    for user in bidders:
        id = user["id"]
        cash = get_random_cash()
        query = f"INSERT INTO bidder VALUES ('{id}', '{cash}'); \n"
        file.write(query)
    file.close()
    print("Finish bidder query")

def create_insert_auctioneer_query():
    global auctioneers
    file = open("sql_scripts/insert_auctioneer_queries.sql", "w", encoding = 'utf-8')

    for user in auctioneers:
        id = user["id"]
        cash = get_random_cash()
        query = f"INSERT INTO auctioneer VALUES ('{id}', '{cash}'); \n"
        file.write(query)
    file.close()
    print("Finish auctioneer query")

def create_insert_product_query():    
    global products, auctioneers, N_PRODUCTS
    
    products = []
    file = open("sql_scripts/insert_product_queries.sql", "w", encoding = 'utf-8')
    for i in range(N_PRODUCTS):
        id = i + 1
        auctioneer_id = rd.choice(auctioneers)["id"]
        name = f"product_name_{i}"
        image_url = f"/images/products/product_{id}.png"
        beginning_price = rd.randint(1000, 9999)
        current_price = beginning_price + rd.randint(1000, 9999)
        created_date = get_random_date()
        sold_date = get_random_date()
        state = get_random_state()
        location = get_random_location()
        product = {
            "id": id,
            "auctioneer_id": auctioneer_id,
            "name": name,
            "image_url": image_url,
            "beginning_price": beginning_price,
            "current_price": current_price,
            "created_date": created_date,
            "sold_date": sold_date,
            "state": state,
            "location": location
        }
        products.append(product)
        query = f"INSERT INTO product VALUES ('{id}', '{auctioneer_id}', '{name}', '{image_url}', '{beginning_price}', '{current_price}', '{created_date}', '{sold_date}', '{state}', '{location}');\n"
        file.write(query)
    file.close()

def create_insert_bidden_price_query():
    global users, products, bidders
    file = open("sql_scripts/insert_bidden_price_queries.sql", "w", encoding = 'utf-8')

    count_id = 0
    rd.shuffle(products)
    for product in products:
        product_id = product["id"]
        subset_bidder_ids = [bidder["id"] for bidder in rd.sample(bidders, rd.randint(3, 10))]
        n_bids = rd.randint(5, 20)
        previous_bidder_id = -1
        previous_price = -1
        previous_datetime = get_random_date()
        for _ in range(n_bids):
            while True:
                bidder_id = rd.choice(subset_bidder_ids)
                if bidder_id != previous_bidder_id:
                    break
            count_id += 1
            price = previous_price + rd.randint(100, 1000)
            created_date = previous_datetime + dt.timedelta(seconds=rd.randint(1000, 8000))
            query = f"INSERT INTO bidden_price VALUES ('{count_id}', '{bidder_id}', '{product_id}', '{price}', '{created_date}'); \n"
            file.write(query)
            previous_bidder_id = bidder_id
            previous_datetime = created_date
            previous_price = price
    file.close()
    print("Finish bidden price query")

def create_insert_users_query():
    global users
    name_marker = set()
    file = open("sql_scripts/insert_user_queries.sql", "w", encoding = 'utf-8')
    count_users = 0
    for user in users:
        id = user["id"]
        name = user["name"]
        username = user["username"]
        if username in name_marker:
            continue
        else:
            name_marker.add(username)
        count_users += 1
        password = user["password"]
        enabled = user["enabled"]
        email = user["email"]
        created_date = user["created_date"]
        last_login = user["last_login"]
        role = user["role"]
        query = f"INSERT INTO user VALUES ('{id}', '{username}', '{password}', '{enabled}', '{email}', '{created_date}', '{last_login}', '{role}'); \n"
        file.write(query)
    file.close()
    print(f"Finish users query, created {count_users}")

def create_insert_authority_query():
    global users
    file = open("sql_scripts/insert_authority_queries.sql", "w", encoding = 'utf-8')
    for user in users:
        id = user["id"]
        username = user["username"]
        authority = f"ROLE_{user["role"]}"
        query = f"INSERT INTO authority VALUES ('{id}', '{username}', '{authority}'); \n"
        file.write(query)
    file.close()
    print(f"Finish authority query")

def create_insert_category_query():    
    global categories, N_CATE

    categories = get_random_categories()
    file = open("sql_scripts/insert_category_queries.sql", "w", encoding = 'utf-8')
    for i in range(N_CATE):
        id = i + 1
        name = categories[i]
        query = f"INSERT INTO category VALUES ('{id}', '{name}'); \n"
        file.write(query)
    file.close()
    print("Finish category query")

def create_insert_product_category_query():
    global products, categories, N_CATE

    file = open("sql_scripts/insert_product_categories_queries.sql", "w", encoding = 'utf-8')
    count = 0
    for product in products:
        this_categories_ids = rd.choices([i + 1 for i in range(len(categories))], k = rd.randint(1, 4))
        for category_id in this_categories_ids:
            count += 1
            query = f"INSERT INTO product_category VALUES ('{count}', '{product["id"]}', '{category_id}'); \n"
        file.write(query)
    file.close()
    print("Finish product category query")

def create_insert_sold_product_query():
    global products, bidders
    file = open("sql_scripts/insert_sold_product_queries.sql", "w", encoding = 'utf-8')
    for product in products:
        if product["state"] == "SOLD":
            bidder = rd.choice(bidders)
            product_id = product["id"]
            bidder_id = bidder["id"]
            query = f"INSERT INTO sold_product VALUES ('{product_id}', '{bidder_id}'); \n"
            file.write(query)
    file.close()

def test_bidder():
    # Connect to the MySQL database
    conn = mysql.connector.connect(
        host=host,
        user=user,
        password=password,
        database=database
    )

    # Create a cursor object
    cursor = conn.cursor(buffered = True)

    start = time()
    for _ in range(10):
        print(f"Time {_}")
        for i in range(1, 1000):
            query = f"SELECT * FROM product WHERE id = {i};"
            # cursor.execute(query) # create new execution plan for each query

            # reuse execution plan
            thresh_hold = rd.randint(1000, 10000)
            cursor.execute("SELECT * FROM product WHERE beginning_price = %s;", (thresh_hold, ))
            # rows = cursor.fetchall()
    print(time() - start)

def execute_query_file(file_path):
    global conn, cursor
    file = open(file_path, "r", encoding='utf-8')
    count_error = 0
    try:
        lines = file.read().split("\n")
        for i in range(len(lines)):
            if i % 1000 == 0:
                print(f"Execute line {i} file {file_path}")
            query = lines[i]
            try:
                cursor.execute(query)
            except Exception as e:
                if count_error < 10:
                    print(f"Error at line {i}: {e}, query : {query}")
                count_error += 1
        conn.commit()
        print(f"Finished execute file {file_path}, queries count : {len(lines)}, count error : {count_error}")
    except Exception as e:
        print(e)
        
def reset_data():
    execute_query_file("sql_scripts/reset_data.sql")

def run_create_data():
    execute_query_file("sql_scripts/insert_user_queries.sql")
    execute_query_file("sql_scripts/insert_auctioneer_queries.sql")
    execute_query_file("sql_scripts/insert_authority_queries.sql")
    execute_query_file("sql_scripts/insert_product_queries.sql")
    execute_query_file("sql_scripts/insert_bidder_queries.sql")
    execute_query_file("sql_scripts/insert_bidden_price_queries.sql")
    execute_query_file("sql_scripts/insert_category_queries.sql")
    execute_query_file("sql_scripts/insert_product_categories_queries.sql")
    execute_query_file("sql_scripts/insert_sold_product_queries.sql")

def config():
    global N_USERS, N_PRODUCTS, N_CATE

    N_USERS = 10000
    N_PRODUCTS = 20000
    N_CATE = 50

if __name__ == "__main__":
    start = time()
    config()
    init_connection()
    create_random_users()
    create_insert_users_query()
    create_insert_authority_query()
    create_insert_bidder_query()
    create_insert_auctioneer_query()
    create_insert_product_query()
    create_insert_authority_query()
    create_insert_category_query()
    create_insert_bidden_price_query()
    create_insert_product_category_query()
    create_insert_sold_product_query()
    reset_data()
    run_create_data()

    # test_bidder()

    print("Time:", time() - start)