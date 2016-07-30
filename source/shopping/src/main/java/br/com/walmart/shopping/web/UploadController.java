package br.com.walmart.shopping.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest")
public class UploadController {
	
	public static final String ROOT = "upload-dir";
	
	@Value("${shopping.limite.upload}")
	private long fileSizeLimit;

	@Value("${shopping.tipos.suportados}")
	private String supportedTypes;
	
	@RequestMapping(path = "/upload", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> uploadInfo(@RequestParam("flowFilename") String fileName, @RequestParam("flowTotalSize") long totalSize) throws IOException {
		if (totalSize > fileSizeLimit) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Tamanho do arquivo excedeu o limite. Máximo permitido: " + fileSizeLimit + " bytes.");
		}
		
		String extension = FilenameUtils.getExtension(fileName).toLowerCase();
		
		if (StringUtils.isEmpty(extension) || !supportedTypes.contains(extension)) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Arquivo não é uma imagem.");
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String uploadImagem(@RequestParam("file") MultipartFile file) throws IOException {
		String nomeArquivo = UUID.randomUUID().toString();
		
		File folder = new File(ROOT);
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(ROOT, nomeArquivo));
		return nomeArquivo;
	}
	
	@RequestMapping(path = "imagem/{filename}", method = RequestMethod.GET)
	public void getFile(@PathVariable String filename, HttpServletResponse response) {
		try {
			FileInputStream fis = new FileInputStream(new File(ROOT, filename));
			
			IOUtils.copy(fis, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			response.setStatus(404);
		}
	}

}
