package com.agrovision;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.WebDetection;
import com.google.cloud.vision.v1.WebDetection.WebEntity;


import com.google.protobuf.ByteString;

public class VisionProcess {
	private String filePath;
	private Map<String,Integer> labels;
	private Map<Float,String> labelsNum;

	
	public VisionProcess(String filePath){
		this.filePath = filePath;
		labels = new TreeMap<String,Integer>();
		labelsNum = new TreeMap<Float,String>(Collections.reverseOrder());
	}
	
	public Map<Float,String> execute() throws IOException, Exception {
		List<AnnotateImageRequest> requests = new ArrayList<>();

		  ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

		  Image img = Image.newBuilder().setContent(imgBytes).build();
		  Feature feat = Feature.newBuilder().setType(Type.WEB_DETECTION).build();
		  AnnotateImageRequest request =
		      AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		  requests.add(request);

		  try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
								
		    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
		    List<AnnotateImageResponse> responses = response.getResponsesList();

		    for (AnnotateImageResponse res : responses) {
		      if (res.hasError()) {
		    	  System.out.println(res.toString());
		        return labelsNum;
		      }

		      WebDetection annotation = res.getWebDetection();
		      
		      for (WebEntity entity : annotation.getWebEntitiesList()) {
		          float value = entity.getScore()*100;
		          labels.put(entity.getDescription(),(int)value);
				  labelsNum.put(entity.getScore() ,entity.getDescription());
		      }
		      
		      }
		    }
		
		return labelsNum;
	}
	

}
