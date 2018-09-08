import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class TeamProcessor {

    public SendMessage process(Message message) throws Exception {
        String teamFounded = "Team " + message.text() + " Not Found";
        JSONParser parser = new JSONParser();

        URL url = new URL ("http://api.football-data.org/v2/teams/");
        Scanner scanner = new Scanner(url.openStream());

        Object obj = parser.parse(scannerToString(scanner));


        JSONObject jsonObject = (JSONObject) obj;
        JSONArray allTeams = (JSONArray) jsonObject.get("teams");

        Iterator iterator = allTeams.iterator();
        while (iterator.hasNext()){
//            printTeamDetails((JSONObject)iterator.next());
            JSONObject currentTeam = (JSONObject) iterator.next();
            if (((String)currentTeam.get("name")).toLowerCase().contains(message.text().toLowerCase())){
                teamFounded = printTeamDetails(currentTeam);
                break;
            }
        }


        SendMessage request = new SendMessage(message.chat().id(), teamFounded)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        return request;
    }

    private String scannerToString (Scanner s){
        StringBuffer stringBuffer = new StringBuffer();
        while (s.hasNext()){
            stringBuffer.append(s.nextLine());
        }

        return stringBuffer.toString();
    }

    private String printTeamDetails (JSONObject team) {
        return (String)team.get("name") + " was founded in " + team.get("founded");
    }
}

