import java.util.LinkedList;
import java.util.Scanner;

public class ContactBookLinkedList {
    public static void main(String[] args){
        // initiate variables and linked lists
        Scanner scanner = new Scanner(System.in);
        LinkedList<String> contact = new LinkedList<>();
        // this second linked list is for deleting a contact when multiple contacts share names
        LinkedList<Integer> tmp = new LinkedList<>();
        boolean loop = true;
        String input_menu;
        String input_name;
        String input_phone_number;
        String input_email;
        String input_choice;
        while(loop) {
            // show menu
            System.out.println("****************************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail search");
            System.out.println("(P)rint list");
            System.out.println("(S)earch");
            System.out.println("(Q)uit");
            System.out.println("****************************");
            System.out.println("Please enter a command: ");
            input_menu = scanner.nextLine();
            switch(input_menu) {
                case "A":
                    // adding contacts
                    // add name
                    System.out.println("Enter the new name: ");
                    input_name = scanner.nextLine();
                    contact.add(input_name);

                    // add phone number
                    System.out.println("Enter the new phone number: ");
                    input_phone_number = scanner.nextLine();
                    contact.add(input_phone_number);

                    // add email
                    System.out.println("Enter the new email: ");
                    input_email = scanner.nextLine();
                    contact.add(input_email);

                    // result
                    System.out.println("New contact successfully saved.");

                    break;
                case "D":
                    // Deleting contacts
                    System.out.println("Enter the name of the contact that you want to delete: ");
                    input_choice = scanner.nextLine();
                    // counter shows the amount of found contacts
                    int counter = 0;
                    // empties tmp
                    tmp.clear();

                    /*
                       since this is the first for loop, I want to explain that i is incremented by 3 because in the
                       linked list, names are stored in index 0 and then every 3 indexes afterwards. Likewise, phone
                       numbers are stored in index 1 and then every 3 indexes afterwards while emails are stored in
                       index 2 and then every 3 indexes afterwards.
                    */

                    for (int i = 0; i < contact.toArray().length; i+=3) {
                        // Shows contact(s) if found
                        if(input_choice.equals(contact.get(i))){
                            tmp.add(i);
                            counter += 1;
                        }
                    }

                    switch(counter) {
                        // if no contacts are found
                        case 0:
                            System.out.println("Contact not found.");
                            break;
                        // if one contact is found
                        case 1:
                            // gets the stored index of the contact
                            int index = tmp.get(0);

                            // previews the contact being deleted
                            System.out.println("Name: " + contact.get(index));
                            System.out.println("Phone number: " + contact.get(index + 1));
                            System.out.println("Email: " + contact.get(index + 2) + "\n");
                            System.out.println("This contact is being deleted... \n");

                            // deletion of contact
                            contact.remove(index + 2);
                            contact.remove(index + 1);
                            contact.remove(index);

                            // result
                            System.out.println("Contact successfully deleted.");
                            break;
                        // more than 1 contact found
                        default:
                            // shows every contact matching the input
                            for (int i = 0; i < tmp.toArray().length; i++) {
                                int index2 = tmp.get(i);
                                System.out.println((i + 1) + ".");
                                System.out.println("Name: " + contact.get(index2));
                                System.out.println("Phone number: " + contact.get(index2 + 1));
                                System.out.println("Email: " + contact.get(index2 + 2) + "\n");
                            }
                            // asks user to specify the contact that is to be deleted
                            System.out.println("Choose the contact you want to delete: (1 to " + tmp.toArray().length + ")");
                            int delete_choice = scanner.nextInt();

                            // becomes true when the user enters a valid input
                            boolean check_valid2 = false;
                            while(!check_valid2) {
                                if (1 <= delete_choice && delete_choice <= tmp.toArray().length) {
                                    check_valid2 = true;

                                    // gets selected index from tmp
                                    int index3 = tmp.get(delete_choice - 1);

                                    // previews the contact being deleted
                                    System.out.println("Name: " + contact.get(index3));
                                    System.out.println("Phone number: " + contact.get(index3 + 1));
                                    System.out.println("Email: " + contact.get(index3 + 2));
                                    System.out.println("This contact is being deleted...\n");

                                    // deletion of contact
                                    contact.remove(index3 + 2);
                                    contact.remove(index3 + 1);
                                    contact.remove(index3);

                                    // result
                                    System.out.println("Contact successfully deleted.");
                                }
                                else {
                                    // asks user to reenter input
                                    System.out.println("Enter a valid input: (1 to " + tmp.toArray().length + ")");
                                    delete_choice = scanner.nextInt();
                                }
                            }
                            System.out.print(scanner.nextLine());
                            break;
                    }
                    break;
                case "E":
                    // Searching with email
                    System.out.println("Enter the email of the contact that you want to search: ");
                    input_email = scanner.nextLine();
                    // check2 remains false if email isn't found
                    boolean check2 = false;
                    // turns true if at least one match is found
                    boolean check_multiple = false;
                    for (int i = 2; i < contact.toArray().length; i += 3) {
                        // if found, show the contact
                        if (input_email.equals(contact.get(i))) {
                            if (!check_multiple) {
                                System.out.println("Here is/are the contact(s) you searched for:");
                                check_multiple = true;
                            }

                            // showing contact(s)
                            System.out.println("Name: " + contact.get(i-2));
                            System.out.println("Phone number: " + contact.get(i-1));
                            System.out.println("Email: " + contact.get(i) + "\n");
                            check2 = true;
                        }

                    }

                    // if the contact doesn't exist
                    if (!check2) {
                        System.out.println("Contact doesn't exist");
                    }

                    break;
                case "P":
                    // Printing list
                    int j = 1;
                    for (int i = 0; i < contact.toArray().length; i+=3) {
                        System.out.println((j) + ".");
                        j += 1;
                        System.out.println("Name: " + contact.get(i));
                        System.out.println("Phone number: " + contact.get(i+1));
                        System.out.println("Email: " + contact.get(i+2) + "\n");
                    }

                    // Shows result
                    if (contact.toArray().length == 0) {
                        System.out.println("The contact list is empty.");
                    }
                    else {
                        System.out.println("All the contacts have been printed.");
                    }

                    break;
                case "S":
                    // Searching for contact with name
                    System.out.println("Enter the name of the contact that you want to search: ");
                    input_name = scanner.nextLine();
                    // check3 remains false if contact isn't found
                    boolean check3 = false;
                    // turns true when a contact is found
                    boolean check_multiple2 = false;
                    for (int i = 0; i < contact.toArray().length; i+=3) {
                        // if found, show the contact
                        if (input_name.equals(contact.get(i))) {
                            if (!check_multiple2) {
                                System.out.println("Here is/are the contact(s) you searched for:");
                                check_multiple2 = true;
                            }
                            System.out.println("Name: " + contact.get(i));
                            System.out.println("Phone number: " + contact.get(i+1));
                            System.out.println("Email: " + contact.get(i+2) + "\n");
                            check3 = true;
                        }
                    }
                    // if contact doesn't exist
                    if (!check3) {
                        System.out.println("Contact doesn't exist");
                    }

                    break;
                case "Q":
                    // Quit
                    loop = false;
                    break;
                default:
                    // If user enters a non-existent command
                    System.out.println("Command doesn't exist.");
                    break;
            }
            // Just to add spacing for visibility
            System.out.println(" ");
        }
    }
}
