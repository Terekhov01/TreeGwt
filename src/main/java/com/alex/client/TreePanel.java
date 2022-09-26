package com.alex.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Set;

public class TreePanel extends Composite {
    interface Binder extends UiBinder<HTMLPanel, TreePanel> {}
    private static final Binder ourUiBinder = GWT.create(Binder.class);

    private static final TreeService treeService = GWT.create(TreeService.class);

    @UiField
    VerticalPanel panel = new VerticalPanel();

    @UiField
    FormPanel form = new FormPanel();

    @UiField
    FileUpload fileUpload = new FileUpload();

    @UiField
    PushButton uploadButton = new PushButton();

    @UiField
    ListBox listBox = new ListBox();

    @UiField
    Tree tree = new Tree();

    @UiField
    HorizontalPanel listPanel = new HorizontalPanel();

    @UiField
    PushButton addTreeItem = new PushButton();

    @UiField
    PushButton createTreeDir = new PushButton();

    @UiField
    TextArea dirName = new TextArea();

    FocusPanel focusPanel = new FocusPanel();
    TreeItem root = new TreeItem();
    Set<String> dirNames;

    public TreePanel(){
        initWidget(ourUiBinder.createAndBindUi(this));

        fileUpload.setName("uploader");

        form.setAction(GWT.getModuleBaseURL()+"fileupload");
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        panel.add(fileUpload);
        panel.add(uploadButton);

        listBox.setMultipleSelect(true);
        listPanel.add(listBox);
        listPanel.add(addTreeItem);
        listPanel.add(createTreeDir);
        dirName.setVisible(true);
        listPanel.add(dirName);

        root.setText("root"); root.setVisible(false);
        tree.addItem(root);
        listPanel.add(tree);

        panel.add(listPanel);
        form.add(panel);

        dirNames = TreeItemUtil.getAllDirNames(root);

        uploadButton.addClickHandler(event -> {
            String filename = fileUpload.getFilename();
            if (filename.length() == 0) {
                Window.alert("No File Specified!");
            } else  if(filename.endsWith(".txt")){
                //submit the form
                form.submit();
            } else {
                Window.alert("File is not a txt-file");
            }
        });

        form.addSubmitCompleteHandler(event -> {
            String[] results = event.getResults().split("\n");
            for (String result : results) {
                listBox.addItem(result);
            }
            listBox.setVisibleItemCount(10);
        });

        listBox.addDoubleClickHandler(event -> {
            addTreeItemFromList();
        });

        addTreeItem.addClickHandler(event -> {
            while (listBox.getSelectedIndex() >= 0){
                addTreeItemFromList();
            }
        });

        createTreeDir.addClickHandler(event -> {
            if (!dirName.getText().isEmpty() && !dirNames.contains(dirName.getText())) {
                if (tree.getSelectedItem() == null) {
                    TreeItem treeItem = new TreeItem();
                    treeItem.setText(dirName.getText());
                    treeItem.setUserObject("dir");
                    root.addItem(treeItem);
                } else if (tree.getSelectedItem().getUserObject() != null
                        && tree.getSelectedItem().getUserObject().equals("dir")) {
                    TreeItem treeItem = new TreeItem();
                    treeItem.setText(dirName.getText());
                    treeItem.setUserObject("dir");
                    tree.getSelectedItem().addItem(treeItem);
                }
            }
            tree.setSelectedItem(null);
        });
    }

    private void addTreeItemFromList(){
        TreeItem treeItem = new TreeItem();
        treeItem.setText(listBox.getSelectedValue());
        root.addItem(treeItem);
        listBox.removeItem(listBox.getSelectedIndex());

        TreeElement treeElement = new TreeElement(treeItem.getText(), ElementType.ELEMENT, null);
        treeService.put(treeElement, new MethodCallback<Void>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Void unused) {

            }
        });
    }
}
