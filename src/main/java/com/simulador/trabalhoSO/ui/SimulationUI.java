package com.simulador.trabalhoSO.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.simulador.trabalhoSO.algoritmos.Clock;
import com.simulador.trabalhoSO.algoritmos.FIFO;
import com.simulador.trabalhoSO.algoritmos.LRU;
import com.simulador.trabalhoSO.algoritmos.NFU;


public class SimulationUI extends JFrame {

    private JTextField txtSequence;
    private JTextField txtFrames;
    private JButton btnSimulate;
    private BarChartPanel chartPanel;
    private JTextArea txtResults;
    private JTextArea txtDetails;

    public SimulationUI() {
        setTitle("Simulador de Substituição de Páginas - Trabalho SO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(root);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        top.add(new JLabel("Sequência de páginas:"));
        txtSequence = new JTextField("7 0 1 2 0 3 0 4 2 3 0 3", 30);
        top.add(txtSequence);

        top.add(new JLabel("Frames:"));
        txtFrames = new JTextField("3", 3);
        top.add(txtFrames);

        btnSimulate = new JButton("Simular");
        btnSimulate.addActionListener(this::onSimulate);
        top.add(btnSimulate);

        root.add(top, BorderLayout.NORTH);

        chartPanel = new BarChartPanel();
        chartPanel.setPreferredSize(new Dimension(600, 350));

        txtResults = new JTextArea();
        txtResults.setEditable(false);
        txtResults.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        txtResults.setBorder(BorderFactory.createTitledBorder("Resumo (faltas)"));

        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.add(chartPanel, BorderLayout.CENTER);

        JScrollPane spResults = new JScrollPane(txtResults);
        spResults.setPreferredSize(new Dimension(250, 350));
        center.add(spResults, BorderLayout.EAST);

        root.add(center, BorderLayout.CENTER);

        txtDetails = new JTextArea();
        txtDetails.setEditable(false);
        txtDetails.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane spDetails = new JScrollPane(txtDetails);
        spDetails.setBorder(BorderFactory.createTitledBorder("Detalhes por algoritmo (frames finais)"));
        spDetails.setPreferredSize(new Dimension(800, 160));

        root.add(spDetails, BorderLayout.SOUTH);
    }

    private void onSimulate(ActionEvent e) {
        String seqText = txtSequence.getText().trim();
        String framesText = txtFrames.getText().trim();

        if (seqText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insira a sequência de páginas.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int frames;
        try {
            frames = Integer.parseInt(framesText);
            if (frames <= 0) {
                throw new NumberFormatException("frames <= 0");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número de frames inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Integer> referencias = parseSequence(seqText);
        if (referencias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sequência inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

         int faltasFIFO, faltasLRU, faltasClock, faltasNFU;
        try {
            faltasFIFO = FIFO.executar(referencias, frames);
            faltasLRU = LRU.executar(referencias, frames);
            faltasClock = Clock.executar(referencias, frames);
            faltasNFU = NFU.executar(referencias, frames);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao executar os algoritmos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

         StringBuilder resumo = new StringBuilder();
        resumo.append(String.format("Frames: %d%n", frames));
        resumo.append(String.format("Sequência: %s%n", seqText));
        resumo.append(String.format("%nFaltas por algoritmo:%n"));
        resumo.append(String.format("FIFO : %d%n", faltasFIFO));
        resumo.append(String.format("LRU  : %d%n", faltasLRU));
        resumo.append(String.format("Clock: %d%n", faltasClock));
        resumo.append(String.format("NFU  : %d%n", faltasNFU));
        txtResults.setText(resumo.toString());

         chartPanel.setValues(new String[]{"FIFO", "LRU", "Clock", "NFU"},
                new int[]{faltasFIFO, faltasLRU, faltasClock, faltasNFU});
 
        StringBuilder detail = new StringBuilder();
        detail.append("Nota: as implementações atuais retornam apenas o número de faltas.\n");
        detail.append("Se desejar ver o estado de cada frame ao longo do tempo, posso adaptar\n");
        detail.append("as classes para fornecer os passos (lista de arrays) durante a simulação.\n\n");
        detail.append("Resultados rápidos:\n");
        detail.append(String.format("FIFO  faltas = %d%n", faltasFIFO));
        detail.append(String.format("LRU   faltas = %d%n", faltasLRU));
        detail.append(String.format("Clock faltas = %d%n", faltasClock));
        detail.append(String.format("NFU   faltas = %d%n", faltasNFU));
        txtDetails.setText(detail.toString());

         chartPanel.repaint();
    }

    private List<Integer> parseSequence(String text) {
        List<Integer> seq = new ArrayList<>();
        String[] tokens = text.trim().split("\\s+");
        for (String t : tokens) {
            if (t.isEmpty()) {
                continue;
            }
            try {
                seq.add(Integer.parseInt(t));
            } catch (NumberFormatException ex) {
             }
        }
        return seq;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationUI ui = new SimulationUI();
            ui.setVisible(true);
        });
    }

   
    static class BarChartPanel extends JPanel {

        private String[] labels = new String[0];
        private int[] values = new int[0];

        public void setValues(String[] labels, int[] values) {
            this.labels = labels;
            this.values = values;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawChart((Graphics2D) g);
        }

        private void drawChart(Graphics2D g2) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, w, h);

            if (values == null || values.length == 0) {
                g2.setColor(Color.DARK_GRAY);
                g2.drawString("Sem dados. Execute a simulação.", 20, 20);
                return;
            }

            int padding = 40;
            int labelSpace = 40;
            int chartWidth = w - 2 * padding;
            int chartHeight = h - padding * 2 - labelSpace;

             int max = 1;
            for (int v : values) {
                if (v > max) {
                    max = v;
                }
            }
             int scaleMax = ((max + 4) / 5) * 5;
            if (scaleMax == 0) {
                scaleMax = 5;
            }

             g2.setColor(Color.BLACK);
            g2.drawLine(padding, padding, padding, padding + chartHeight);
            g2.drawLine(padding, padding + chartHeight, padding + chartWidth, padding + chartHeight);

             int gridLines = 5;
            for (int i = 0; i <= gridLines; i++) {
                int y = padding + chartHeight - (i * chartHeight / gridLines);
                g2.setColor(new Color(220, 220, 220));
                g2.drawLine(padding + 1, y, padding + chartWidth, y);
                g2.setColor(Color.BLACK);
                String label = String.valueOf(i * scaleMax / gridLines);
                g2.drawString(label, 6, y + 5);
            }

             int n = values.length;
            int gap = 20;
            int barAreaWidth = chartWidth - (n + 1) * gap;
            if (barAreaWidth < 0) {
                barAreaWidth = 0;
            }
            int barWidth = n == 0 ? 0 : barAreaWidth / n;

            for (int i = 0; i < n; i++) {
                int v = values[i];
                double frac = (double) v / (double) scaleMax;
                int barHeight = (int) (frac * chartHeight);

                int x = padding + gap + i * (barWidth + gap);
                int y = padding + chartHeight - barHeight;

                Color[] palette = new Color[]{new Color(0x4E79A7), new Color(0xF28E2B), new Color(0xE15759), new Color(0x76B7B2)};
                Color barColor = palette[i % palette.length];
                g2.setColor(barColor);
                g2.fillRect(x, y, barWidth, barHeight);

                g2.setColor(Color.BLACK);
                String sval = String.valueOf(v);
                int strW = g2.getFontMetrics().stringWidth(sval);
                g2.drawString(sval, x + Math.max(0, (barWidth - strW) / 2), Math.max(padding + 10, y - 6));

                String lbl = labels[i];
                int lblW = g2.getFontMetrics().stringWidth(lbl);
                g2.drawString(lbl, x + Math.max(0, (barWidth - lblW) / 2), padding + chartHeight + 20);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            String title = "Comparação de faltas por algoritmo";
            int tw = g2.getFontMetrics().stringWidth(title);
            g2.drawString(title, padding + (chartWidth - tw) / 2, 18);
        }
    }
}
