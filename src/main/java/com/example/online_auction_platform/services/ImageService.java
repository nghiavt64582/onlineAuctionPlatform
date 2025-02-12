package com.example.online_auction_platform.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private static final String STATIC_RESOURCE_PATH = "src/main/resources/static";
    private static final String PRODUCT_POSTFIX = "/images/products/product_%d.png";
    private static final String AVATAR_POSTFIX = "/images/avatars/avatar_%d.png";

    public String saveImage(MultipartFile image, String imagePath) throws IOException {

        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Image file is empty or null.");
        }

        // 1. Create the directory if it doesn't exist
        Path directory = Paths.get(imagePath).getParent(); // Get the parent directory
        if (!Files.exists(directory)) {
            Files.createDirectories(directory); // Create the directory and any necessary parent directories
        }

        // Build file part
        Path filePath = Paths.get(imagePath);

        // 3. Transfer the file
        image.transferTo(filePath.toFile());

        // 4. Return the full path of the saved image (or just the file name)
        return filePath.toString(); // Or just fileName if you prefer
    }

    public String addImage(MultipartFile file) throws IOException {
        // if success, return image path
        String nextProductPath = findNextAvailableProductPath();
        if (nextProductPath != null) {
            saveImage(file, nextProductPath);
            return nextProductPath;
        } else {
            return null;
        }
    }

    public String findNextAvailableProductPath() {
        for (int idx = 1; idx <= 1000000; idx ++) {
            String filePathSt = String.format(STATIC_RESOURCE_PATH + PRODUCT_POSTFIX, idx);
            Path filePath = Path.of(filePathSt);
            if (!Files.exists(filePath)) {
                return filePath.toString(); // Found the next available path
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ImageService imageService = new ImageService();
        String nextProductPath = imageService.findNextAvailableProductPath();
        System.out.println(nextProductPath);
    }

}
