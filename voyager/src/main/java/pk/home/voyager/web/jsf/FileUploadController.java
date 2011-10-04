package pk.home.voyager.web.jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadController")
@RequestScoped
public class FileUploadController {

	private static final int BUFFER_SIZE = 512;

	public void handleFileUpload(FileUploadEvent event) {
		// FacesMessage msg = new FacesMessage("Succesful",
		// event.getFile().getFileName() + " is uploaded.");
		// FacesContext.getCurrentInstance().addMessage(null, msg);

		//System.out.println(event.getFile().getFileName() + " is uploaded.");

		//ExternalContext extContext = FacesContext.getCurrentInstance()
		//		.getExternalContext();

		//File result = new File(extContext.getRealPath("//tmp")
				//+ "//" + event.getFile().getFileName());
		
		File result = new File("//tmp//" + event.getFile().getFileName());

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(result);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bulk;
			InputStream inputStream = event.getFile().getInputstream();
			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();
			FacesMessage msg = new FacesMessage("Succesful", event.getFile()
					.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			//System.out.println(event.getFile(). + " is uploaded.");
		} catch (IOException e) {
			e.printStackTrace();
			FacesMessage error = new FacesMessage(
					"The files were   			not uploaded!");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}

	}
}