/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.PorzionePeso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	
    	String partenza= this.boxPorzioni.getValue();
    	String passiS= txtPassi.getText();
    	if(passiS==null) {
    		txtResult.appendText("inserisci un valore!");
    		return;
    	}
    	
    	int passi;
    	try {
    		passi = Integer.parseInt(passiS);
    	} catch(NumberFormatException ne) {
    		txtResult.appendText("errore : inserisci un numero");
    		return;
    	}
    	
    	txtResult.appendText("CAMMINO CON PESO MASSIMO: "+"\n");
    List<String> cammino = this.model.trovaPercorso(partenza,passi);
    for(String s: cammino) {
    	txtResult.appendText(s+"\n");
    	
    }
    txtResult.appendText("PESO MASSIMO: "+this.model.getPesoMigliore()+"\n");
    
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	String tipoScelta = boxPorzioni.getValue();
    	if(tipoScelta==null) {
    		txtResult.appendText("errore : devi selezionare una porzione");
    		return;
    	}
    	txtResult.appendText("PORZIONI CORRELATE A "+tipoScelta+"\n");
    	List<PorzionePeso> correlate = this.model.getPorzioniCorrelate(tipoScelta);
    	for(PorzionePeso s: correlate) {
    		txtResult.appendText(s+"\n");
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	
    	String calS= txtCalorie.getText();
    	if(calS==null) {
    		txtResult.appendText("errore: devi inserire un valore");
    		return;
    	}
    	
    	int cal;
    	try {
    		cal= Integer.parseInt(calS);
    	}catch(NumberFormatException ne) {
    		txtResult.appendText("le calorie devono essere un numero");
    		return;
    	}
    	this.model.creaGrafo(cal);
    	txtResult.appendText("GRAFO CREATO!"+"\n");
    	txtResult.appendText("# vertici "+this.model.getNumVertici()+"\n");
    	txtResult.appendText("#archi "+this.model.getNumArchi()+"\n");
    	boxPorzioni.getItems().addAll(this.model.getVerticiTendina());
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
