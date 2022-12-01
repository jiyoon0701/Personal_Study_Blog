package spring.community.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3UploaderService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // aws 이미지 경로 : ID / 블로그 ID (여러 개)/ 사진 URL (UUID + 파일 이름)

    public String upload(MultipartFile file, String dirName) throws IOException {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        String storedFilePath = dirName + "/" + UUID.randomUUID()+"."+ext;
                //+ file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3Client.putObject(bucket, storedFilePath, file.getInputStream(), metadata);
        return amazonS3Client.getUrl(bucket, storedFilePath).toString();
    }
}
