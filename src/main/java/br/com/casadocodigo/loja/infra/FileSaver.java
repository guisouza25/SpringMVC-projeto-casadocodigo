package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class FileSaver {

	@Autowired
	private AmazonS3 amazonS3;
	
	private static final String BUCKET = "imagens-casadocodigo";
	
	@Autowired
	private HttpServletRequest request;
	

	public String saveS3(MultipartFile file) {
		
		try {
						
			amazonS3.putObject(new PutObjectRequest(
					BUCKET, 
					file.getOriginalFilename(),
					file.getInputStream(), null
					).withCannedAcl(CannedAccessControlList.PublicRead));
			
		} catch (IllegalStateException | IOException e) {
			
			throw new RuntimeException();
		}
		
		return "https://imagens-casadocodigo.s3-sa-east-1.amazonaws.com/" + file.getOriginalFilename();
	}
	
	
	public String write(String baseFolder, MultipartFile file) {
		
		try {
			String realPath = request.getServletContext().getRealPath("/resources/" + baseFolder);
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			
			System.out.println(baseFolder);
			System.out.println(realPath);
			System.out.println(path);
			
			System.out.println(request.getServletContext().getContextPath());
			
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String uploadToAwsS3(MultipartFile file) 
			throws AmazonServiceException, AmazonClientException, IOException {
		
		Region saEast1 = Region.getRegion(Regions.SA_EAST_1);
		amazonS3.setRegion(saEast1);
        
        String bucketName = "casadocodigo-imagens";
        String key = file.getOriginalFilename();
		ObjectMetadata fileMetadata = new ObjectMetadata();
		fileMetadata.setContentLength(file.getSize());
        
		amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), fileMetadata));
        
        String sumarioPath = "https://casadocodigo-imagens.s3-sa-east-1.amazonaws.com/" + key;
        return sumarioPath;
	}
	
	
}
