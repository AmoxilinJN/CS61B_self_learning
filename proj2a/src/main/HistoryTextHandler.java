package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

import static utils.Utils.*;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ngm;
    
    public HistoryTextHandler(NGramMap ngm) {
        this.ngm = ngm;
    }
    
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        
        StringBuilder response = new StringBuilder();
        
        // 为每个单词获取权重历史并格式化输出
        for (String word : words) {
            TimeSeries weightHistory = ngm.weightHistory(word, startYear, endYear);
            response.append(word).append(": ").append(weightHistory.toString()).append("\n");
        }
        
        return response.toString();
    }
}