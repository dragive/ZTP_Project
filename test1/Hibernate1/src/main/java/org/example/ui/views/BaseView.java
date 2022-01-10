package org.example.ui.views;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Slf4j
public class BaseView extends JPanel{

    protected JPanel mainContainerPanel;
    protected JFrame frame;

    private JPanel headerPane;
    private JPanel bodyPane;

    private JPanel northPane;
    private JPanel centerPane;
    private JPanel footerPane;

    private JPanel headerInsidePane;
    private JPanel northInsidePane;
    private JPanel centerInsidePane;
    private JPanel footerInsidePane;

    private Boolean mounted = Boolean.FALSE;

    public BaseView(LayoutManager layoutManager) {
        this.setMinimumSize(new Dimension(400,300));
        this.setLayout(layoutManager);
        this.setVisible(true);
        this.setFocusable(true);
        if(!this.getClass().toString().contains("MenuPanel")) MenuPanel.bottomPanel=this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseReleased(e);
                requestFocus();
            }
        });
        this.mainContainerPanel = this;
    }

    @Override
    public Component add(Component comp) {
        if(!mounted)
            return super.add(comp);
        else
            return this.northInsidePane.add(comp);
    }

    @Override
    public void add(Component comp,Object constrains) {
        comp.setMinimumSize(new Dimension(4,10));
        if(constrains instanceof BaseViewConstraint){
            this.add(comp,(BaseViewConstraint)constrains);
        }
        else{
            super.add(comp,constrains);
        }
    }

    public void add(Component component,BaseViewConstraint baseViewConstraint){
        log.info("Component"+component.toString()+"  baseviewC "+baseViewConstraint );
        if(BaseViewConstraint.NORTH.equals(baseViewConstraint)){
            addNorth(component);
        }
        else if(BaseViewConstraint.CENTER.equals(baseViewConstraint)){
            addCenter(component);
        }
        else if(BaseViewConstraint.FOOTER.equals(baseViewConstraint)){
            log.info("added element to footer");
            addFooter(component);
        }
        else if(BaseViewConstraint.HEADER.equals(baseViewConstraint)){
            addHeader(component);
        }
    }

    protected void mountPanels(){

        super.setLayout(new BorderLayout());
        instantiateAllPanes();

        this.bodyPane = new JPanel(new BorderLayout());

        this.bodyPane.add(this.northPane,BorderLayout.NORTH);
        this.bodyPane.add(this.centerPane,BorderLayout.CENTER);
        this.bodyPane.add(this.footerPane,BorderLayout.SOUTH);

        super.add(headerPane,BorderLayout.NORTH);

        this.northPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.centerPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.footerPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        this.headerPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//
//        northPane.setBackground(Color.CYAN);
//        centerPane.setBackground(Color.GREEN);
//        footerPane.setBackground(Color.YELLOW);

//        super.add(headerPane,BorderLayout.NORTH);

        headerPane.setBackground(Color.magenta);


        super.add(bodyPane,BorderLayout.CENTER);

        this.mounted = Boolean.TRUE;

    }

    public void setLayout(LayoutManager layoutManager2, BaseViewConstraint baseViewConstraint ){

        if(BaseViewConstraint.NORTH.equals(baseViewConstraint)){
            this.northInsidePane.setLayout(layoutManager2);
        }
        else if(BaseViewConstraint.CENTER.equals(baseViewConstraint)){
            this.centerInsidePane.setLayout(layoutManager2);
        }
        else if(BaseViewConstraint.FOOTER.equals(baseViewConstraint)){
            this.footerInsidePane.setLayout(layoutManager2);
        }
        else if(BaseViewConstraint.HEADER.equals(baseViewConstraint)){
            this.headerInsidePane.setLayout(layoutManager2);
        }
    }

    protected void addNorth( Component component){
        if(northPane == null)
            instantiateNorthPane();
        this.northInsidePane.add(component);
    }

    protected void addCenter(Component component){
        if(centerPane == null)
            instantiateCenterPane();
        this.centerInsidePane.add(component);
    }

    protected void addFooter(Component component){
        if(footerPane == null)
            instantiateFooterPane();

        log.info("AddFooter");
        this.footerInsidePane.add(component);
    }

    protected void addHeader(Component component){
        if(headerPane == null)
            instantiateHeaderPane();
        this.headerInsidePane.add(component);
    }



    private void instantiateNorthPane(){
        this.northPane = new JPanel(new BorderLayout()); //główny kontener na górze


        JPanel inside = new JPanel(new GridLayout(0,1));
        JScrollPane jScrollPane = new JScrollPane(inside);

//        jScrollPane.add(inside);
        this.northPane.add(jScrollPane,BorderLayout.NORTH);
        this.northInsidePane = inside;
    }

    private void instantiateCenterPane(){
        this.centerPane = new JPanel(new GridBagLayout());



        JPanel inside = new JPanel(new GridLayout(0,1));
        JScrollPane jScrollPane = new JScrollPane(inside);

//        jScrollPane.add(inside);
        this.centerPane.add(jScrollPane,new GridBagConstraints());
        this.centerInsidePane = inside;
    }

    private void instantiateFooterPane(){
        this.footerPane = new JPanel(new BorderLayout());



        JPanel inside = new JPanel(new GridLayout(0,1));
        JScrollPane jScrollPane = new JScrollPane(inside);

//        jScrollPane.add(inside);
        this.footerPane.add(jScrollPane,BorderLayout.CENTER);
        this.footerInsidePane = inside;
    }

    private void instantiateHeaderPane(){
        this.headerPane = new JPanel(new BorderLayout());


        JPanel inside = new JPanel(new GridLayout(0,1));
        JScrollPane jScrollPane = new JScrollPane(inside);

        jScrollPane.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        this.headerPane.add(jScrollPane,BorderLayout.CENTER);
        this.headerInsidePane = inside;
    }

    private void instantiateAllPanes(){
        instantiateHeaderPane();
        instantiateNorthPane();
        instantiateCenterPane();
        instantiateFooterPane();
    }


}
