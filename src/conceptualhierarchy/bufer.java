/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptualhierarchy;

/**
 *
 * @author Anatoly
 */
public class bufer {
      /*  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String conceptName = nameTextField.getText().trim();
        if (conceptName.equals("")){
            ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Введите имя!");
            errorD.setVisible(true);
            return;
        }
        String conceptComment = ""+commentTextField.getText().trim();
        if (!newConceptInd){
            if (!conceptPropertiesIsChanged()){
                if (conceptIsRenamed())
                    if (!ActualData.avalibleConceptName(conceptName)){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                        errorD.setVisible(true);
                        return;
                    }
                    else {
                        ActualData.renameConcept(concept.getName(), conceptName);
                        concept.setName(conceptName); 
                        concept.setComment(conceptComment);
                    }
            }
            else {
                ArrayList<String> newProp = new ArrayList();
                for (int i = 0; i < propertyTable.getRowCount(); i++){
                    String property = ((String) propertyTable.getValueAt(i, 0)).trim();
                    if (property.equals("")){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Заполните все поля в таблице!");
                        errorD.setVisible(true);
                        return; 
                    }
                    newProp.add(property);
                }
                if (conceptIsRenamed())
                    if (!ActualData.avalibleConceptName(conceptName)){
                        ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                        errorD.setVisible(true);
                        return;
                    }
                if (ActualData.conceptWithThisPropertiesIsExist(newProp)){
                    ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с такими свойствами уже есть!");
                    errorD.setVisible(true);
                    return;
                }
                else {
                    ActualData.removeConceptByName(concept.getName());
                    String oldName = concept.getName();
                    String oldComment = concept.getComment();
                    ArrayList<String> oldCharacteristics = (ArrayList<String>) concept.getCharacteristics().clone();
                    concept.setName(conceptName);
                    concept.setComment(conceptComment);
                    concept.setCharacteristics(newProp);
                    ActualData.addConceptToHierarchy(concept);
                    if (!ActualData.addingIsSucces()){
                        ActualData.removeConceptByName(concept.getName());
                        concept.setName(oldName);
                        concept.setComment(oldComment);
                        concept.setCharacteristics(oldCharacteristics);
                        ActualData.addConceptToHierarchy(concept);
                        new ErrorDialog(null, true, "Множественное наследование концептов запрещено!\nКонцепт не изменен").setVisible(true);
                        return;
                    }
                }
            }
        }
        else{
            ArrayList<String> newProp = new ArrayList();
            for (int i = 0; i < propertyTable.getRowCount(); i++){ 
                String property = ((String) propertyTable.getValueAt(i, 0)).trim();
                if (property.equals("")){
                    ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Заполните все поля в таблице!");
                    errorD.setVisible(true);
                    return;
                }
                newProp.add(property);
            }
            if (!ActualData.avalibleConceptName(conceptName)){
                ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с таким именем уже есть!");
                errorD.setVisible(true);
                return;
            }
            else if (ActualData.conceptWithThisPropertiesIsExist(newProp)){
                ErrorDialog errorD = new ErrorDialog(new javax.swing.JFrame(), true, "Концепт с такими свойствами уже есть!");
                errorD.setVisible(true);
                return;
            }
            else {
                concept = new Concept();
                concept.setName(conceptName);
                concept.setComment(conceptComment);
                concept.setCharacteristics(newProp);
                ActualData.addConceptToHierarchy(concept);
                if (!ActualData.addingIsSucces()){
                    new ErrorDialog(null, true, "Множественное наследование концептов запрещено!\nКонцепт не добавлен").setVisible(true);
                    return;
                }
            }
    }
    setVisible(false);
    dispose(); 
    }                                        
*/
}
