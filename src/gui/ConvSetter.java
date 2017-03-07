package gui;

public class ConvSetter {
	
	private final int[] chronoM = {13, 1, 15, 2, 10, 4, 5, 16, 6, 11,
			7, 14, 8, 3, 12, 9};
	private final int[] terM = {1, 2};
	private final int[] aaronM = {1, 2, 3};
	private final int[] pennyM = {13, 14};
	private final int[] aurM = {4, 5};
	private final int[] nowM = {6};
	private final int[] malM = {7};
	private final int[] othM = {10, 11, 12};
	private final int[] oscM = {8, 9};
	private final int[][] match = {chronoM, terM, aaronM, pennyM,
			aurM, nowM, malM, othM, oscM};
	
	private ChatLogData log;
	
	public ConvSetter(){
		
	}
	
	/**
	 * 
	 * @param category
	 * @param convo
	 * @return
	 */
	public ChatLogData getLog(int category, int convo){
		setConvo(match[category][convo]);
		return log;
	}
	
	/**
	 * 
	 * @param category
	 * @return
	 */
	public ChatLogData getLog(int category){
		for(int convo: match[category]){
			setConvo(match[category][convo]);
		}
		return log;
	}
	
	public String[] getConvNames(int category){
		String[] names = new String[match[category].length];
		for(int iter = 0; iter < match[category].length; iter++){
			int convo = match[category][iter];
			setConvo(convo);
			names[iter] = log.getName();
		}
		return names;
	}
	
	/**
	 * 
	 * @param matched
	 * @return
	 */
	private void setConvo(int matched){
		switch(matched){
		case 1:
			log = ChatLogData.AARON1;
			break;
		case 2:
			log = ChatLogData.AARON2;
			break;
		case 3:
			log = ChatLogData.AARON3;
			break;
		case 4:
			log = ChatLogData.AUR1;
			break;
		case 5:
			log = ChatLogData.AUR2;
			break;
		case 6:
			log = ChatLogData.NOWELL;
			break;
		case 7:
			log = ChatLogData.MALCOLM;
			break;
		case 8:
			log = ChatLogData.OSCAR1;
			break;
		case 9:
			log = ChatLogData.OSCAR2;
			break;
		case 10:
			log = ChatLogData.OTHER1;
			break;
		case 11:
			log = ChatLogData.OTHER2;
			break;
		case 12:
			log = ChatLogData.OTHER3;
			break;
		case 13:
			log = ChatLogData.PENNY1;
			break;
		case 14:
			log = ChatLogData.PENNY2;
			break;
		case 15:
			log = ChatLogData.TER1;
			break;
		case 16:
			log = ChatLogData.TER2;
			break;
		default:
			break;
		}
	}

	public String[] getConvNames(String name) {
		String[] names = null;
		String[] defaultSet = {"Chronological Order", "Terence", "Aaron", "Penny", "Aurora", "Malcolm", "Nowell", "Oscar"};
		for(int i = 0; i < defaultSet.length; i++){
			String h = defaultSet[i];
			if (name == h){
				names = getConvNames(i);
			}
		}
		return names;
		// TODO Auto-generated method stub
	}
} 