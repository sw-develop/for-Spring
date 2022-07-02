package com.example.mvc.controller

import com.example.mvc.model.http.FileUploadRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class FileUploadApiController {

    @PostMapping(
            value = ["/file-upload"],
            consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun fileUpload(
        @RequestPart request: FileUploadRequest,
        @RequestPart image: MultipartFile
    ): String? {
        println(image.originalFilename)
        return image.originalFilename
    }
}

