from PIL import Image, ImageDraw, ImageFont
import os
import random as rd

# Configuration
num_images = 100  # Number of images
output_dir = "avatars"
image_path_prefix = "avatar"
img_size = (200, 200)  # Image size (width, height)
bg_color = (255, 255, 255)  # White background
text_color = (0, 0, 0)  # Black text
font_size = 40

# Create output directory if it doesn't exist
os.makedirs(output_dir, exist_ok=True)

def get_random_color():
    r = rd.randint(1, 150)
    g = rd.randint(1, 150)
    b = rd.randint(1, 150)
    return (r, g, b)

# Load font (use default if TTF not found)
try:
    font = ImageFont.truetype("arial.ttf", font_size)
except IOError:
    font = ImageFont.load_default()

# Generate images
for i in range(num_images):
    img = Image.new("RGB", img_size, bg_color)
    draw = ImageDraw.Draw(img)
    
    text = f"avatar_{i}"
    bbox = draw.textbbox((0, 0), text, font=font)  # Get bounding box
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]
    text_x = (img_size[0] - text_width) // 2
    text_y = (img_size[1] - text_height) // 2

    draw.text((text_x, text_y), text, fill = get_random_color(), font=font)

    # Draw four lines to form a border
    top_left = (0, 0)
    top_right = (img_size[0] - 1, 0)
    bottom_left = (0, img_size[1] - 1)
    bottom_right = (img_size[0] - 1, img_size[1] - 1)

    border_color = get_random_color()
    border_width = rd.randint(5, 10)
    draw.line([top_left, top_right], fill = border_color, width = border_width)  # Top
    draw.line([top_left, bottom_left], fill = border_color, width = border_width)  # Left
    draw.line([bottom_left, bottom_right], fill = border_color, width = border_width)  # Bottom
    draw.line([top_right, bottom_right], fill = border_color, width = border_width)  # Right


    img.save(os.path.join(output_dir, f"{image_path_prefix}_{i}.png"))

print(f"{num_images} images created in '{output_dir}' directory.")
