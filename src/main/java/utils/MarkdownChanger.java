package utils;

/**
 *
 */
public class MarkdownChanger {

    //For ParseMode.MARKDOWNV2
    public static String replaceAllSpecialCharacters(String text) {
        if(text.contains("_")){
            text = text.replaceAll("\\_", "\\\\_");
        }
        if(text.contains("*")){
            text = text.replaceAll("\\*", "\\\\*");
        }
        if(text.contains("(")){
            text = text.replaceAll("\\(", "\\\\(");
        }
        if(text.contains(")")){
            text = text.replaceAll("\\)", "\\\\)");
        }
        if(text.contains("[")){
            text = text.replaceAll("\\[", "\\\\[");
        }
        if(text.contains("]")){
            text = text.replaceAll("\\]", "\\\\]");
        }
        if(text.contains(".")){
            text = text.replaceAll("\\.", "\\\\.");
        }
        if(text.contains("~")){
            text = text.replaceAll("\\~", "\\\\~");
        }
        if(text.contains("`")){
            text = text.replaceAll("\\`", "\\\\`");
        }
        if(text.contains("<")){
            text = text.replaceAll("\\<", "\\\\<");
        }
        if(text.contains(">")){
            text = text.replaceAll("\\>", "\\\\>");
        }
        if(text.contains("#")){
            text = text.replaceAll("\\#", "\\\\#");
        }
        if(text.contains("+")){
            text = text.replaceAll("\\+", "\\\\+");
        }
        if(text.contains("-")){
            text = text.replaceAll("\\-", "\\\\-");
        }
        if(text.contains("=")){
            text = text.replaceAll("\\=", "\\\\=");
        }
        if(text.contains("|")){
            text = text.replaceAll("\\|", "\\\\|");
        }
        if(text.contains("{")){
            text = text.replaceAll("\\{", "\\\\{");
        }
        if(text.contains("}")){
            text = text.replaceAll("\\}", "\\\\}");
        }
        if(text.contains("!")){
            text = text.replaceAll("\\!", "\\\\!");
        }
        return text;
    }
}
