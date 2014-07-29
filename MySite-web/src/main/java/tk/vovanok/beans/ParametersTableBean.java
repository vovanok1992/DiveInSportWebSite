package tk.vovanok.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import tk.vovanok.entities.commons.ShipmentParameter;

@ViewScoped
@Named
public class ParametersTableBean implements Serializable{

    private int counterId = 0;
    private List<ShipmentParameter> params = new ArrayList<>();
    private ShipmentParameter selectedShipmentParameter;

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ячейка изменена", "Старое значение: " + oldValue + ", Новое:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void addField() {
        params.add(new ShipmentParameter("...", "...",params.size()));
    }

    public void removeField(ShipmentParameter sp) {
        params.remove(sp);
    }
    
    public void removeAll() {
        params.clear();
    }

    public List<ShipmentParameter> getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public ShipmentParameter getSelectedShipmentParameter() {
        return selectedShipmentParameter;
    }

    public void setSelectedShipmentParameter(ShipmentParameter selectedShipmentParameter) {
        this.selectedShipmentParameter = selectedShipmentParameter;
    }

}
