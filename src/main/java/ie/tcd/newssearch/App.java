package ie.tcd.newssearch;

import ie.tcd.newssearch.builder.AnalyzerChoice;
import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.builder.SimilarityChoice;
import ie.tcd.newssearch.searcher.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, ParseException {

        AnalyzerChoice analyzerChoice;
        SimilarityChoice similarityChoice;

        System.out.flush();
        System.out.println(" ");
        System.out.print("===CS7IS3 Group Assignment-2===");
        System.out.println("Choose Analyzer");
        System.out.println("1 - Custom");
        System.out.println("2 - Standard");
        System.out.println("3 - English");
        System.out.println("4 - Simple");

        String input = System.console().readLine();

        switch (input) {
            case "1":
                analyzerChoice = AnalyzerChoice.Custom;
                break;
            case "2":
                analyzerChoice = AnalyzerChoice.Standard;
                break;
            case "3":
                analyzerChoice = AnalyzerChoice.English;
                break;
            case "4":
                analyzerChoice = AnalyzerChoice.Simple;
                break;
            default:
                analyzerChoice = AnalyzerChoice.Standard;
                break;
        }

        System.out.println("Choose Similarity");
        System.out.println("1 - BM25Similarity");
        System.out.println("2 - MultiSimilarity");
        System.out.println("3 - LMDirichlet");
        System.out.println("4 - Boolean");

        input = System.console().readLine();

        switch (input) {
            case "1":
                similarityChoice = SimilarityChoice.BM25;
                break;
            case "2":
                similarityChoice = SimilarityChoice.Multi;
                break;
            case "3":
                similarityChoice = SimilarityChoice.LMDirichlet;
                break;
            case "4":
                similarityChoice = SimilarityChoice.Boolean;
                break;
            default:
                similarityChoice = SimilarityChoice.Classic;
                break;
        }
        IndexerCore.indexLocation = "Index/";
        IndexerCore.CreateIndex(analyzerChoice, similarityChoice);
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir, analyzerChoice, similarityChoice);

    }
}
