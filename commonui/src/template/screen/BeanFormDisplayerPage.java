/*
 * BeanFormDisplayerPage.java
 *
 * Created on March 11, 2009, 6:30 PM
 */
package template.screen;

import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import rule.BusinessRuleWrapper;
import service.util.AbstractIBean;
import springbean.AAAConfig;
import template.UITemplate;
import util.BeanUtil;

/**
 *
 * @author  Charliemagne Mark
 */
public class BeanFormDisplayerPage extends AbstractTemplatePanel {

    public AbstractIBean mybean;

    public void showSubRecords() {
//        super.constructTabs();
        pnl.removeAll();
        pnl.setLayout(new GridLayout());
        pnl.add(tabChildren);
        pnl.updateUI();
    }

    public static AbstractTemplatePanel setupTemplate(Class bean) {
        try {
            UITemplate template = (UITemplate) bean.getAnnotation(UITemplate.class);
            Class templateCls = BeanFormDisplayerPage.class;
            AbstractTemplatePanel tmp = AbstractTemplatePanel.getInstance(templateCls, template);
            tmp.setBean(bean);
            tmp.ruleWrapper = BusinessRuleWrapper.getInstance(tmp);
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(bean.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static JPanel getFormPanel(AbstractIBean bean) {
        BeanFormDisplayerPage view = (BeanFormDisplayerPage) setupTemplate(bean.getClass());
        view.mybean = bean;
        JPanel pnl = (JPanel) view.getMainForm();
        view.loadSubRecords(bean);
        return pnl;
    }

    public static JPanel getFormPanel(AbstractIBean bean, AbstractIBean holder) {
        BeanUtil.copyBean(bean, holder);
        BeanFormDisplayerPage view = (BeanFormDisplayerPage) setupTemplate(holder.getClass());
        view.mybean = holder;
        JPanel pnl = (JPanel) view.getMainForm();
        view.loadSubRecords(bean);
        return pnl;
    }

    public static void displayBeanForm(AbstractIBean bean) {
        AAAConfig.getInstance();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add(getFormPanel(bean));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void bindPallete(Object pallete) {
    }

    @Override
    public void searchNow(int moreCount) {
        //just put the bean
        list.add(mybean);
        tblResult.updateUI();
    }

//    @Override
//    protected void constructTabs() {
//    }

    @Override
    public Object getMainForm() {
        super.getMainForm();
        pnl.setLayout(new GridLayout());
        if (template.showImages()) {
        	setupImages();
            pnl.add(pnlMainForm);
            pnl.add(imagePallete);
        } else {
            pnl.add(pnlMainForm);
        }
        if (constants.Constants.panelBackground != null) {
            pnl.setBackground(constants.Constants.panelBackground);
//            tabChildren.setBackground(constants.Constants.panelBackground);
        }
        tblResult.setRowSelectionInterval(0, 0);
        return pnl;
    }

    @Override
    public Object getMainSearch() {
        return new JPanel();
    }

    @Override
    public Object getMainSearchResult() {
        return new JPanel();
    }

    /** Creates new form BeanFormDisplayerPage */
    public BeanFormDisplayerPage() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl = new javax.swing.JPanel();

        pnl.setName("pnl"); // NOI18N
        pnl.setLayout(new java.awt.BorderLayout());

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridLayout(1, 0));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnl;
    // End of variables declaration//GEN-END:variables

}
