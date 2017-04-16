package castle;

import java.util.HashMap;
import java.util.Scanner;


public class Game {
    private Room currentRoom;
    private HashMap<String , Handler> handlers = new HashMap<String, Handler>();
        
    public Game() 
    {
    	handlers.put("go", new HandlerGo(this));
    	handlers.put("bye", new HandlerBye(this));
    	handlers.put("help", new HandlerHelp(this));
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;
      
        //	���췿��
        outside = new Room("�Ǳ���");
        lobby = new Room("����");
        pub = new Room("С�ư�");
        study = new Room("�鷿");
        bedroom = new Room("����");
        
        //	��ʼ������ĳ���
//        outside.setExits(null, lobby, study, pub);
//        lobby.setExits(null, null, null, outside);
//        pub.setExits(null, outside, null, null);
//        study.setExits(outside, bedroom, null, null);
//        bedroom.setExits(null, null, null, study);
        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up",pub);
        pub.setExit("down", lobby);
        currentRoom = outside;  //	4�ӳǱ����⿪ʼ
    }

    private void printWelcome() {     //2
        System.out.println();
        System.out.println("��ӭ�����Ǳ���");
        System.out.println("����һ���������ĵ���Ϸ��");
        System.out.println("�����Ҫ������������ 'help' ��");
        System.out.println();
        ShowPrompt();
    }
    
    public void ShowPrompt(){    //3
    	System.out.println("��������" + currentRoom); //4current�õ���tostring����
    	System.out.print("�����У�");
        System.out.print(currentRoom.getExitDesc());   //5�����һ���з���ķ���
        System.out.println();
    }
    // ����Ϊ�û�����
    public void play(){
    	Scanner in = new Scanner(System.in);
    	 while ( true ) {
     		String line = in.nextLine();
     		String[] words = line.split(" ");
     		Handler handler = handlers.get(words[0]);
     		String value = "";
     		if(words.length>1){
     			value = words[1];
     		}
     		if(handler != null){
     			handler.doCmd(value);
     			if(handler.isBye())
     				break;
     		}
     		
//     		if ( words[0].equals("help") ) {
//     			game.printHelp();
//     		} else if (words[0].equals("go") ) {
//     			game.goRoom(words[1]);
//     		} else if ( words[0].equals("bye") ) {
//     			break;
//     		}
    	 }
    	 in.close();
    }
    	
//    private void printHelp() 
//    {
//        System.out.print("��·������������������У�go bye help");
//        System.out.println("�磺\tgo east");
//    }

    public void goRoom(String direction)  //6
    {
        Room nextRoom = currentRoom.getExit(direction);  //7��main����������һ��������ж����ڷ������һ������
        
        if (nextRoom == null) {
            System.out.println("����û���ţ�");
        }
        else {
            currentRoom = nextRoom;   //8����һ�����丳�����ڷ����ټ���ִ��
            ShowPrompt();          //9һ��ѭ������
        }
    }
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.printWelcome();      //1
        game.play();
       
        
        
        System.out.println("��л���Ĺ��١��ټ���");
        
	}

}