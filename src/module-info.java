module otomasyon {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.web;
	
	opens application to javafx.graphics, javafx.fxml;
}
