package eu.bde.sc7pilot.imageaggregator;

import java.io.File;
import java.util.List;

import eu.bde.sc7pilot.imageaggregator.model.Image;

public class DownloadService {

	private String username;
	private String password;
	
	public DownloadService(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
    public void downloadImages(List<Image> images, String outputDirectory) {
    	// We will first download the quick-looks (images 2, 3) because they are faster and then the SENTINEL-1 ones (0 and 1 in List). 
        DataClient imageService = new DataClient(username, password);
        // Downloading Sentinel2 Quick-Look-Images for ground truth visualization
        for (int i = 2; i < 4; i++) {
        	Image image = images.get(i);
            String imageName = image.getName();
            try {
            	long startDownl = System.currentTimeMillis();
            	System.out.println("\nDownloading image:\t" + image.getName());
                imageService.downloadQuickLook(image.getId(), outputDirectory + File.separator + imageName + ".jpeg");
                long endDownl = System.currentTimeMillis();
                long downlTime = endDownl - startDownl;
                System.out.println(downlTime + " ms for Downloading and saving Image No.: " + i);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Downloading Sentinel1 Images for image processing
        for (int i = 0; i < 2; i++) {
        	Image image = images.get(i);
            String imageName = image.getName();
            try {
            	long startDownl = System.currentTimeMillis();
            	System.out.println("\nDownloading image:\t" + image.getName());
                imageService.downloadAndSaveById(image.getId(), outputDirectory + File.separator + imageName + ".zip");
                long endDownl = System.currentTimeMillis();
                long downlTime = (endDownl - startDownl)/60000;
                System.out.println(downlTime + " mins for Downloading and saving Image No.: " + i);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}