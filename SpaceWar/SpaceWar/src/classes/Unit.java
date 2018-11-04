package classes;
import java.io.*;

import javafx.animation.*;
import javafx.application.*;
import javafx.beans.value.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.util.*;

/**
 * 
 * @author jaehyeon
 *
 */
public class Unit {
	
	private String name;
	private ImageView imageView;
	private String job;
	private int maxHp, hp;
	private int power, armor;
	private int attackRange;
	private int moveRange;
	private int x, y;
	private int attackCount;
	private boolean movable, attackable;
	
	private Pane stage;	// 보드가 존재하는 레이아웃
	private Unit[][] board; // 보드(유닛 배열)
	
	private Player player; // 유닛을 소유한 플레이어
	private MediaPlayer soundHit; // 피격 효과음
	private MediaPlayer soundShoot; // 공격 효과음
	
	Label labelHp = new Label(); // 남은 hp를 표시할 레이블
	Rectangle hpBar = new Rectangle(30, 3); // hp 게이지
	
	/**
	 * 
	 * @param name unit name
	 * @param job unit job
	 * @param hp unit hp
	 * @param power unit power
	 * @param armor unit armor
	 * @param x unit position x
	 * @param y unit position y
	 * @param attackRange unit attack range
	 * @param moveRange unit move range
	 * @param attackCount unit attack count. unit damage formular : (power - armor) * attackCount
	 */
	public Unit(
			String name, 
			String job,
			int hp, 
			int power, 
			int armor,
			int x, 
			int y, 
			int attackRange, 
			int moveRange,
			int attackCount
			){
		this.name = name;
		this.job = job;
		this.maxHp = this.hp = hp;
		this.power = power;
		this.armor = armor;
		this.x = x;
		this.y = y;
		this.attackRange = attackRange;
		this.moveRange = moveRange;
		this.attackCount = attackCount;
		
		this.movable = false;
		this.attackable = false;
	}
	
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPower() {
		return power;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int getAttackCount() {
		return attackCount;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getMoveRange() {
		return moveRange;
	}
	
	public int getAtttackRange() {
		return attackRange;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getJob() {
		return job;
	}
	
	public boolean getMovable() {
		return movable;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean getAttackable() {
		return attackable;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	/**
	 * 소유할 플레이어를 설정하는 메소드
	 * @param player 유닛을 소유한 플레이어
	 */
	public void setPlayer(Player player) {
		this.player = player;
		this.board = player.getBoard().getArray();
		this.stage = player.getStage();
		
		this.board[x][y] = this;
		
		// 유닛 이미지 초기화
		imageView = new ImageView(new Image(getClass().getResource("/images/" + job + ".png").toString()));
		imageView.setLayoutX(x * 32);
		imageView.setLayoutY(y * 32);
		// 플레이어 번호가 1일 경우 붉은 이펙트, 2일 경우 푸른 이펙트
		imageView.setStyle("-fx-effect: dropshadow(three-pass-box, "+ (player.getNumber() == 1 ? "rgba(255, 0, 0, 0.7)" : "rgba(0, 0, 255, 0.7)") + ", 5, 0.9, 0, 1);");
		stage.getChildren().add(imageView);

		/**********************
		 *  hp 레이블 초기화
		 **********************/
		labelHp.setStyle(
				"-fx-text-fill: " + (player.getNumber() == 1 ? "red" : "blue") +";" 
				+ "-fx-effect: dropshadow(gaussian, white, 1, 1, 0, 0);"
				);
		labelHp.setLayoutX(imageView.getLayoutX());
		labelHp.setLayoutY(imageView.getLayoutY() + 15);
		
		/**********************
		 *  hp 게이지 초기화
		 **********************/
		hpBar.setLayoutX(imageView.getLayoutX() + 1);
		hpBar.setLayoutY(imageView.getLayoutY() + 28);
		hpBar.setStyle("-fx-fill: linear-gradient(to right, red 100%, black 0%);");
		
		// 유닛 이미지가 움직이면 hp 레이블과 게이지도 같이 움직이도록하는 리스너
		imageView.layoutXProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				labelHp.setLayoutX(newValue.doubleValue());
				hpBar.setLayoutX(newValue.doubleValue() + 1);
			}
		});
		imageView.layoutYProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				labelHp.setLayoutY(newValue.doubleValue() + 15);
				hpBar.setLayoutY(newValue.doubleValue() + 28);
			}
		});
		labelHp.setText(String.valueOf(hp));
		
		Platform.runLater(()->{
			stage.getChildren().addAll(labelHp, hpBar);			
		});
	}
	
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(int x, int y) {
		// 원래 있던 위치를 null로 설정, 새 위치에 유닛 배치
		board[this.x][this.y] = null; 
		board[x][y] = this;
		
		this.x = x;
		this.y = y;
		
		// 유닛 이동 애니메이션
		KeyValue moveX = new KeyValue(imageView.layoutXProperty(), x * 32);
		KeyValue moveY = new KeyValue(imageView.layoutYProperty(), y * 32);
		KeyFrame moveXFrame = new KeyFrame(Duration.millis(250), moveX);
		KeyFrame moveYFrame = new KeyFrame(Duration.millis(250), moveY);
		
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().addAll(moveXFrame, moveYFrame);
		
		timeline.play();
	}
	
	public void attack(Unit target) {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(80), ev->{ 
			// 탄 발사 이펙트.
			Timeline timeline2 = new Timeline();

			// 탄 이미지를 현재 유닛 위치로 불러옴
			ImageView bulletImage = new ImageView(getClass().getResource("/images/bullet.png").toString());
			bulletImage.setLayoutX(this.imageView.getLayoutX());
			bulletImage.setLayoutY(this.imageView.getLayoutY());
			stage.getChildren().add(bulletImage);
			
			timeline2.getKeyFrames().addAll(
					new KeyFrame(Duration.millis(120), new KeyValue(bulletImage.layoutXProperty(), target.x * 32)),
					new KeyFrame(Duration.millis(120), new KeyValue(bulletImage.layoutYProperty(), target.y * 32))
					);
			
			// 탄 발사 이펙트가 끝나면 대상 유닛에게 데미지를 입히고 탄을 제거
			timeline2.setOnFinished(event->{
				target.damaged(power);
				Platform.runLater(()->{
					stage.getChildren().remove(bulletImage);
				});
			});
			
			// 탄 발사 효과음 재생
			soundShoot = new MediaPlayer(new Media(getClass().getResource("/sounds/shoot.wav").toString()));
			soundShoot.play();
			
			// 탄 발사 이펙트 재생
			timeline2.play();
		})); 
			
		// 공격 회수만큼 공격 반복
		timeline.setCycleCount(this.attackCount);
		timeline.play();
	}
	
	public void heal(Unit target) {
		Timeline timeline = new Timeline();
		
		// 회복 이펙트를 생성 후, 위치를 현재 유닛의 중심으로 설정
		Circle c = new Circle(5, Color.LIGHTGREEN);
		c.setLayoutX(this.x * 32 + 16);
		c.setLayoutY(this.y * 32 + 16);
		c.setOpacity(0.7);

		Platform.runLater(()->{
			stage.getChildren().add(c);
		});
		 
		// 회복 애니메이션
		KeyValue keyValueX = new KeyValue(c.layoutXProperty(), target.x * 32 + 16);
		KeyValue keyValueY = new KeyValue(c.layoutYProperty(), target.y * 32 + 16);
		
		KeyFrame keyFrameX = new KeyFrame(Duration.millis(100), keyValueX);
		KeyFrame keyFrameY = new KeyFrame(Duration.millis(100), keyValueY);
		
		
		timeline.getKeyFrames().addAll(keyFrameX, keyFrameY);
		timeline.setOnFinished(ev->{
			// 회복 이펙트가 대상 유닛에 도착 시, 이펙트가 커지다가 소멸
			Timeline timeline2 = new Timeline();
			KeyValue keyValue2 = new KeyValue(c.radiusProperty(), 8);
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), keyValue2);
			
			timeline2.getKeyFrames().add(keyFrame2);

			// 회복 이펙트 소멸 시 대상 유닛을 회복시키고 이펙트 제거
			timeline2.setOnFinished(ev2->{
				Platform.runLater(()->{
					stage.getChildren().remove(c);
				});
				target.healed(this.power);
			});
			timeline2.play();
		});
		timeline.play();
	}
	
	public void healed(int amount) {
		// 실제로 회복되는 수치(최대 체력을 넘길 수 없음)
		int healedAmount = Math.min(this.maxHp - this.hp, amount); 
		
		// 회복 수치 표시 애니메이션 (수치가 유닛으로부터 위로 상승 후 사라짐)
		Timeline timeline = new Timeline();
		Label labelHealed = new Label(String.valueOf(healedAmount));
		labelHealed.setLayoutX(this.x * 32 + 10);
		labelHealed.setLayoutY(this.y * 32 + 10);
		labelHealed.setStyle(
				"-fx-text-fill: white; "
				+ "-fx-font-size: 14;" 
				+ "-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);"
				);

		KeyValue keyValue = new KeyValue(labelHealed.layoutYProperty(), labelHealed.getLayoutY() - 20);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(250), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.setOnFinished(ev->{
			Platform.runLater(()->{
				stage.getChildren().remove(labelHealed);
			});
		});
		stage.getChildren().add(labelHealed);
		timeline.play();
		
		// 체력 회복 및 체력 표시 업데이트
		this.hp = this.hp + healedAmount;
		this.updateHp();	
	}
	
	private void updateHp() {
		Platform.runLater(()->{
			// 체력 레이블의 수치를 남은 체력으로 변경
			this.labelHp.setText(String.valueOf(this.hp));
			
			// 현재 체력의 퍼센티지를 구함
			double remain = 100.0 * hp / maxHp;
			hpBar.setStyle("-fx-fill: linear-gradient(to right, red " + remain + "%, black 0%);");	
		});
	}
	
	int cnt = 0, speed = -3;
	public void damaged(int atk) {
		// 데미지 계산
		int dmg = Math.max(1, atk - this.armor);
		
		ImageView img = this.imageView;

		{ // 피격 모션 (좌우 흔들림)
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), ev->{
				img.setLayoutX(img.getLayoutX() + speed);
				
				if(speed < 0){
					cnt -= 1;
				} else {
					cnt += 1;
				} 
				if(cnt == -2 || cnt == 2) speed = -speed;
			}));
			
			timeline.setCycleCount(8);
			timeline.setOnFinished(ev->{
				soundHit = new MediaPlayer(new Media(getClass().getResource("/sounds/hit.wav").toString()));
				soundHit.play();
			});
			timeline.play();
		}
		
		{ // 데미지 수치 표시
			Timeline timeline = new Timeline();
			Label labelDamage = new Label(String.valueOf(dmg));
			labelDamage.setLayoutX(this.x * 32 + 10);
			labelDamage.setLayoutY(this.y * 32 + 10);
			labelDamage.setStyle(
					"-fx-text-fill: white; "
					+ "-fx-font-size: 14;" 
					+ "-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);"
					);

			KeyValue keyValue = new KeyValue(labelDamage.layoutYProperty(), labelDamage.getLayoutY() - 20);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(250), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.setOnFinished(ev->{
				Platform.runLater(()->{
					stage.getChildren().remove(labelDamage);
				});
			});
			stage.getChildren().add(labelDamage);
			timeline.play();
		}
		
		// 유닛 체력 감소 및 체력 표시 업데이트
		this.hp = Math.max(0, this.hp - dmg);
		this.updateHp();
		
		// hp가 0이 될 경우 사망
		if(hp == 0) {
			this.die();
		}
	}
	
	
	
	public void die() {
		Platform.runLater(()->{
			this.board[x][y] = null; // 보드에서 유닛 객체 제거
			
			// 유닛 이미지를 무덤으로 변경
			stage.getChildren().remove(this.imageView); 
			this.imageView.setImage(new Image(getClass().getResource("/images/grave.png").toString()));
			stage.getChildren().add(0, this.imageView);
			
			// 무덤 위에 Dead 문자열 표시
			stage.getChildren().remove(labelHp);
			this.labelHp.setFont(new Font(10));
			this.labelHp.setText("Dead");
			stage.getChildren().add(1, this.labelHp);

			// 공격 및 이동 불가 설정
			this.movable = this.attackable = false;
			
			// 체력 게이지 제거
			stage.getChildren().remove(hpBar);
		});
	}

	
	
	
}
