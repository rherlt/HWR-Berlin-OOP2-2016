import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class WebcamSample {

	public static void main(String[] args) throws InterruptedException {

		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		webcam.addWebcamListener(new WebcamListener() {

			int imageNumber = 0;

			@Override
			public void webcamOpen(WebcamEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void webcamImageObtained(WebcamEvent arg0) {
				// TODO Auto-generated method stub
				imageNumber++;
				System.out.println("Image " + imageNumber + " Captured!");
			}

			@Override
			public void webcamDisposed(WebcamEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void webcamClosed(WebcamEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);

		JFrame window = new JFrame("Test webcam panel");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);

	}
}
