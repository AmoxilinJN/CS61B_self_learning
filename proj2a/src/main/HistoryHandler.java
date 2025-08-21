package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap ngm;
    
    public HistoryHandler(NGramMap map) {
        ngm = map;
    }
    
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        
        ArrayList<TimeSeries> timeSeriesList = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        
        // 为每个单词获取权重历史数据
        for (String word : words) {
            TimeSeries weightHistory = ngm.weightHistory(word, startYear, endYear);
            if (weightHistory != null && !weightHistory.isEmpty()) {
                timeSeriesList.add(weightHistory);
                labels.add(word);
            }
        }
        
        // 生成图表并转换为base64编码
        XYChart chart = Plotter.generateTimeSeriesChart(labels, timeSeriesList);
        String encodedImage = Plotter.encodeChartAsString(chart);
        
        return encodedImage;
    }
}