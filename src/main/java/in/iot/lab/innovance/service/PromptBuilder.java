package in.iot.lab.innovance.service;

import in.iot.lab.innovance.entity.UserLevelChoice;

import java.util.List;

public class PromptBuilder {
    
    public static String buildPrompt(List<UserLevelChoice> choices) {
        String domainName = choices.getFirst().getUser().getDomain().getName();
        StringBuilder promptBuilder = new StringBuilder("In the domain of ").append(domainName).append(", ");
        
        String choiceTemplate = "you selected '%s' for level %s. Options available were [%s]. ";
        
        for (UserLevelChoice choice : choices) {
            String optionsString = String.join(", ", choice.getLevel().getOptions());
            int selectedIndex = choice.getSelected();
            
            String selectedOption = (selectedIndex >= 0 && selectedIndex < choice.getLevel().getOptions().size()) ? choice.getLevel().getOptions().get(selectedIndex) : "Invalid selection";
            
            String levelName = choice.getLevel().getQuestion();
            promptBuilder.append(String.format(choiceTemplate, selectedOption, levelName, optionsString));
        }
        
        promptBuilder.append("Based on the user selections, roast them as brutally as you can under 100 words!");
        promptBuilder.append(" Rather than roasting each level individually, consider the selections as a whole and craft a single, cohesive roast.");
        
        return promptBuilder.toString();
    }
}
