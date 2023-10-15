// package com.sahh.userscrud;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import com.amazonaws.services.s3.AmazonS3;
// import com.sahh.userscrud.file_upload.FileUploadService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.mock.web.MockMultipartFile;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.io.InputStream;

// public class FileUploadServiceTest {

// private FileUploadService fileUploadService;
// private AmazonS3 mockS3Client;

// @BeforeEach
// void setUp() {
// mockS3Client = mock(AmazonS3.class);
// fileUploadService = new FileUploadService(mockS3Client);
// }

// @Test
// void testUploadFileSuccess() throws IOException {
// String bucketName = "emp-file";
// String fileName = "testFile.txt";
// byte[] fileContent = "Hello, World!".getBytes();
// InputStream inputStream = new ByteArrayInputStream(fileContent);
// MultipartFile multipartFile = new MockMultipartFile("file", fileName,
// "text/plain", inputStream);

// when(mockS3Client.doesBucketExistV2(bucketName)).thenReturn(true);
// when(mockS3Client.putObject(eq(bucketName), eq(fileName),
// any(InputStream.class), any())).thenReturn(null);
// boolean result = fileUploadService.uploadFile(multipartFile);
// assertTrue(result);
// verify(mockS3Client).putObject(eq(bucketName), eq(fileName),
// any(InputStream.class), any());
// }

// @Test
// void testUploadFileBucketDoesNotExist() throws IOException {
// String fileName = "testFile.txt";
// byte[] fileContent = "Hello, World!".getBytes();
// InputStream inputStream = new ByteArrayInputStream(fileContent);
// MultipartFile multipartFile = new MockMultipartFile("file", fileName,
// "text/plain", inputStream);
// when(mockS3Client.doesBucketExistV2(any())).thenReturn(false);
// boolean result = fileUploadService.uploadFile(multipartFile);
// assertFalse(result);
// verify(mockS3Client, never()).putObject(any(), any(), any(InputStream.class),
// any());
// }
// }
