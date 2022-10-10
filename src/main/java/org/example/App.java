package org.example;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */

class Error {

    @SerializedName("row_num")
    private String rowNum;
    @SerializedName("error_description")
    private String errorDescription;

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}

public class App 
{

    public static void main( String[] args )
    {
        List<Error> errors = new ArrayList<>();
        String html = "<table id=\\\"error\\\"><tr><th>Row</th><th>Error</th></tr><tr><td>5</td><td>Error 1</td></tr><tr><td>8</td><td> Error 2</td></tr></table>";
        Element table = Jsoup.parse(html);
        Elements rows = table.select("tr");
        rows.remove(0);
        for (Element row: rows) {
            Error error = new Error();
            error.setRowNum(row.select("td").get(0).text());
            error.setErrorDescription(row.select("td").get(1).text());
            errors.add(error);
        }
        System.out.println(new Gson().toJson(errors));
    }
}
