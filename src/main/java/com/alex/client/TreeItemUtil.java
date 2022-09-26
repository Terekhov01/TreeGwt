package com.alex.client;

import com.google.gwt.user.client.ui.TreeItem;

import java.util.*;

public class TreeItemUtil {

    private static List<TreeItem> fetchChildren(TreeItem ti){
        List<TreeItem> children = new ArrayList<>();

        for (int i = 0; i < ti.getChildCount(); ++i)
            children.add(ti.getChild(i));
        return children;
    }

    public static List<TreeItem> traverseDfs(TreeItem root){
        Stack<TreeItem> children = new Stack<>();
        children.add(root);
        List<TreeItem> allItems = new ArrayList<>();

        while (!children.isEmpty()){
            TreeItem current = children.pop();
            List<TreeItem> currentChildren = fetchChildren(current);
            children.addAll(currentChildren);
        }
        return allItems;
    }

    public static Set<String> getAllDirNames(TreeItem root){
        Set<String> dirNames = new HashSet<>();
        List<TreeItem> itemList = traverseDfs(root);
        for (TreeItem item : itemList) {
            if(item.getUserObject().equals("dir")) {
                dirNames.add(item.getText());
            }
        }
        return dirNames;
    }
}
