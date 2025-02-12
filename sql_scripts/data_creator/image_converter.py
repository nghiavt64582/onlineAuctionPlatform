import base64

def image_to_base64(image_path):
    with open(image_path, "rb") as image_file:
        base64_string = base64.b64encode(image_file.read()).decode('utf-8')
    return base64_string

# Example usage:
image_path = "example.jpg"  # Change to your image path
base64_str = image_to_base64(image_path)
print(len(base64_str), base64_str[:100])  # Output Base64 string
