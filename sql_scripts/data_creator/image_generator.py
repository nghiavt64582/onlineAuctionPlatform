from PIL import Image, ImageDraw, ImageFont
import os

# Configuration
num_images = 100  # Number of images
output_dir = "avatars"
img_size = (200, 200)  # Image size (width, height)
bg_color = (255, 255, 255)  # White background
text_color = (0, 0, 0)  # Black text
font_size = 40

# Create output directory if it doesn't exist
os.makedirs(output_dir, exist_ok=True)

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

    draw.text((text_x, text_y), text, fill=text_color, font=font)

    img.save(os.path.join(output_dir, f"avatar_{i}.png"))

print(f"{num_images} images created in '{output_dir}' directory.")
