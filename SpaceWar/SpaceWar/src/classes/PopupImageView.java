package classes;

import javafx.animation.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.*;


/**
 * 
 * @author jaehyeon
 *
 */
public class PopupImageView extends ImageView {
	
	public interface ReleasedListener {
		public void ImageReleased(ImageView source, double x, double y);
	}
	
	private double x, y; 
	
	private ImageView poppedImage; // 드래그되는 이미지를 표현하기 위한 이미지뷰 객체
	private ReleasedListener listener; // 마우스를 뗄 경우 발생하는 리스너
	
	public PopupImageView() {
	}
	
	public PopupImageView(Pane pane) {		
		setPane(pane);
	}
	
	public void setPane(Pane pane){
		this.setOnMousePressed(ev->{
			// 마우스가 클릭된 위치 저장
			x = ev.getSceneX();
			y = ev.getSceneY();
			
			// 이미지를 마우스 클릭된 위치가 중심이 되도록 복제
			ImageView img = new ImageView();
			img.setImage(this.getImage());

			img.setFitWidth(img.getBoundsInParent().getWidth());
			img.setFitHeight(img.getBoundsInParent().getHeight());
			img.setLayoutX(x - img.getBoundsInParent().getWidth() * 1.5 / 2);
			img.setLayoutY(y - img.getBoundsInParent().getHeight() * 1.5 / 2);
			img.setOpacity(0.3);
			img.setUserData(PopupImageView.this);
			
			poppedImage = img;
			pane.getChildren().add(img);
			
			// 복제된 이미지가 원본 이미지보다 1.5배 크기까지 점점 커짐
			Timeline t = new Timeline();
			KeyValue kv = new KeyValue(img.fitWidthProperty(), img.getBoundsInParent().getWidth() * 1.5);
			KeyValue kv2 = new KeyValue(img.fitHeightProperty(), img.getBoundsInParent().getHeight() * 1.5);
			KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
			KeyFrame kf2 = new KeyFrame(Duration.millis(50), kv2);
			t.getKeyFrames().addAll(kf, kf2);
			t.play();
		});
		
		// 마우스가 드래그 될 때 마다 이미지도 마우스를 따라 이동
		this.setOnMouseDragged(ev->{
			x = ev.getSceneX();
			y = ev.getSceneY();
			if(poppedImage != null){
				poppedImage.setLayoutX(x - poppedImage.getBoundsInParent().getWidth() / 2);
				poppedImage.setLayoutY(y - poppedImage.getBoundsInParent().getHeight() / 2);
			}
		});
		
		// 마우스를 뗄 때 복제된 이미지를 제거하고 부착된 리스너를 실행
		this.setOnMouseReleased(ev->{
			if(poppedImage != null) {
				pane.getChildren().remove(poppedImage);
				poppedImage = null;
				
				if(this.listener != null) {
					this.listener.ImageReleased(this, x, y);
				}
			}
		});
	}
	
	public void addReleasedListener(ReleasedListener listener){
		this.listener = listener;
	}
	
	
}
