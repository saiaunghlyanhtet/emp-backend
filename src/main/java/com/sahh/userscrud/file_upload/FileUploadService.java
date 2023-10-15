package com.sahh.userscrud.file_upload;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {

    private final AmazonS3 s3Client;

    private static final String bucketName = "emp-file";

    @Value("${linode.endpoint}")
    String endpoint;

    public FileUploadService(@Value("${linode.accessKey}") String accessKey,
            @Value("${linode.secretKey}") String secretKey,
            @Value("${linode.endpoint}") String endpoint) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "us-east-1"))
                .build();

    }

    // Uncomment this for unit test
    // public FileUploadService(AmazonS3 s3Client) {
    //     this.s3Client = s3Client;
    // }

    public boolean uploadFile(MultipartFile file) throws IOException {
        if (s3Client.doesBucketExistV2(bucketName)) {
            s3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), null);
            return true;
        } else {
            return false;
        }

    }
}
