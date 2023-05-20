package com.tup.buensabor.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImagenService {

    @Autowired
    private Cloudinary cloudinary;

    public Map<String, Object> uploadImage(MultipartFile imagen) throws IOException {
        File file = File.createTempFile("temp", null);
        imagen.transferTo(file);

        var params = ObjectUtils.asMap(
                "public_id", "productos/productoTest",
                "overwrite", true,
                "resource_type", "image"
        );
        return cloudinary.uploader().upload(file, params);
    }

}
