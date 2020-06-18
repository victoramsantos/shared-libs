package libs.utils

import static pipeline.context.PipelineContext.echo

class Log{
    static void info(String message){
        logging("INFO", message)
    }

    static void warning(String message){
        logging("WARNING", message)
    }

    static void error(String message){
        logging("ERROR", message)
    }

    private static void logging(String severity, String message){
        echo( "[$severity] $message")
    }

}
