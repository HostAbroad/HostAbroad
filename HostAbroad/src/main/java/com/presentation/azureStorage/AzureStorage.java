package com.presentation.azureStorage;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

public class AzureStorage {
	
	private static final String storageConnectionString =
			"DefaultEndpointsProtocol=https;"
			+ "AccountName=hostabroad;"
			+ "AccountKey=Qx23t4FsFQCOOZtvkgyLgEeSa6Uz+/W8Mv+Ril55yiFN6MHsarXU9MBaiHlaQgrxEKHnfLzIdNDj3c05rHfuzQ==;EndpointSuffix=core.windows.net";
	
	public static void initializeService() {
		File sourceFile = loadImage(), downloadedFile = null;
		
		System.out.println("Azure Blob storage quick start sample");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try {    
			// Parse the connection string and create a blob client to interact with Blob storage
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("hostabroad");

			// Create the container if it does not exist with private access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.OFF, new BlobRequestOptions(), new OperationContext());		    

//			//Creating a sample file
//			sourceFile = File.createTempFile("sampleFile", ".txt");
//			System.out.println("Creating a sample file at: " + sourceFile.toString());
//			Writer output = new BufferedWriter(new FileWriter(sourceFile));
//			output.write("Hello Azure!");
//			output.close();

			//Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());
			
			//Creating blob and uploading file to it
			System.out.println("Uploading the sample file ");
			blob.uploadFromFile(sourceFile.getAbsolutePath());
			
			//Listing contents of container
			for (ListBlobItem blobItem : container.listBlobs()) {
			System.out.println("URI of blob is: " + blobItem.getUri());
				
		}

		// Download blob. In most cases, you would have to retrieve the reference
		// to cloudBlockBlob here. However, we created that reference earlier, and 
		// haven't changed the blob we're interested in, so we can reuse it. 
		// Here we are creating a new file to download to. Alternatively you can also pass in the path as a string into downloadToFile method: blob.downloadToFile("/path/to/new/file").
		downloadedFile = new File(sourceFile.getParentFile(), "downloadedFile.txt");
		blob.downloadToFile(downloadedFile.getAbsolutePath());
		} 
		catch (StorageException ex)
		{
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
		}
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		finally 
		{
			System.out.println("The program has completed successfully.");
			System.out.println("Press the 'Enter' key while in the console to delete the sample files, example container, and exit the application.");

			//Pausing for input
			Scanner sc = new Scanner(System.in);
			sc.nextLine();

			System.out.println("Deleting the container");
			try {
				if(container != null)
					container.deleteIfExists();
			} 
			catch (StorageException ex) {
				System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
			}

			System.out.println("Deleting the source, and downloaded files");

			if(downloadedFile != null)
				downloadedFile.deleteOnExit();

			if(sourceFile != null)
				sourceFile.deleteOnExit();

			//Closing scanner
			sc.close();
		}
	}
	
	public static File loadImage() {
		File img = null;
		img = new File("C:\\Users\\Sany\\Desktop\\HostAbroad\\HostAbroad\\src\\main\\webapp\\WEB-INF\\images\\logo.png");
		System.out.println("Image read");
		
		return img;
	}

	public static File readImageFromStorage(CloudBlockBlob blob) {
		OutputStream os = null;
		byte[] buffer = null;
		try {
			blob.downloadToByteArray(buffer, 0);
		} catch (StorageException e) {
			e.printStackTrace();
			System.out.println("Can't read the file");
		}
		
		String fileName = "logo.png";

		BufferedOutputStream bs = null;

		try {
			File a = new File(fileName);
		    FileOutputStream fs = new FileOutputStream(a);
		    bs = new BufferedOutputStream(fs);
		    bs.write(buffer);
		    bs.close();
		    bs = null;

		} catch (Exception e) {
		    e.printStackTrace();
		}

		if (bs != null) try { bs.close(); } catch (Exception e) {}
		
		
		
		
		File file = new File("https://hostabroad.blob.core.windows.net/hostabroad/logo.png");
		System.out.println("can I read the file:" + file.canRead());
		return file;
	}
}
