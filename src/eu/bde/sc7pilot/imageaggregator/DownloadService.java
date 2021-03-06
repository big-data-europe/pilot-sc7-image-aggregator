package eu.bde.sc7pilot.imageaggregator;

import java.util.List;

import eu.bde.sc7pilot.imageaggregator.model.Image;

/**
 *
 * @author efi
 */

public class DownloadService {

	private String username;
	private String password;
	public DownloadService(String username,String password) {
		this.username=username;
		this.password=password;
	}
    public void downloadImages(List<Image> images, String outputDirectory) {
    	System.out.println("images to download: "+images.size());
        DataClient imageService = new DataClient(username, password);
        for (int i=0;i<images.size();i++) {
        	Image image=images.get(i);
            String imageName = image.getName();
            try {
                imageService.downloadAndSaveById(image.getId(), outputDirectory + imageName + ".zip");
                System.out.println("Image "+i+" was saved successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       
    }
}
