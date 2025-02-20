## Blob!! User Guide
***
![Ui.png](Ui.png)

Hello! I'm Blob!! Blob helps you do the things that you are too lazy to do yourself...like tracking your tasks in a list!!
It's text-based, easy to learn and FREE to use! Perfect for the ultimate ~~procrastinator~~ busy student who has too many tasks to remember! 
Come, give Blob a try :)

## Features
***

## 1. Add ToDo Tasks
***
To add a ToDo task, you can use the ```todo``` command followed by the task description.\
Format: ```todo [task description]```\
Examples: 
- ```todo do homework```
- ```todo buy broccoli```
- ```todo study for exam```

Expected output (last example):
```
Got it. I've added this task:
    [T][ ] study for exam
Now you have 1 tasks in the list.
```

## 2. Add Deadlines 
***
To add a Deadline task, you can use the ```deadline``` command followed by the task description and the due date in the format yyyy/MM/dd HHmm or MMM dd yyyy HH:mm.\
Format: ```deadline [task description] /by [due date in required format]```\
Examples:
- ```deadline submit quiz /by 2025/02/24 0900```
- ```deadline download github /by 2025/01/14 0000```
- ```deadline buy broccoli /by Mar 03 2025 18:30```

Expected output (last example):
```
Got it. I've added this task:
    [D][ ] buy broccoli (by: Mar 03 2025 18:30)
Now you have 1 tasks in the list.
```

## 3. Add Events 
***
To add an Event task, you can use the ```event``` command followed by the task description, event start time and event end time.\
Format: ```event [task description] /from [event start time] /to [event end time]```\
Examples:
- ```event CCA /from Sat 1200 /to Sat 1400```
- ```event mahjong session /from Fri 1800 /to Fri 2000```
- ```event birthday celebration /from 3 Apr 1800 /to 3 Apr 2200```

Expected output (last example):
```
Got it. I've added this task:
    [E][ ] birthday celebration (from: 3 Apr 1800 to: 3 Apr 2200)
Now you have 1 tasks in the list.
```


## 4. Find tasks based on keyword 
***
To find tasks based on keyword, you can use the ```find``` command followed by the keyword.\
Format: ```find [keyword]```\
Examples:
- ```find broccoli```
- ```find mahjong```
- ```find exam```

Expected output (last example):
```
Here are the matching tasks: 
[T][ ] study for exam
```


## 5. Delete task 
***
To delete specific tasks, you can use the ```delete``` command followed by the task number, which is based on input sequence.\
Format: ```delete [task number]```\
Examples:
- ```delete 2```: deletes 2nd task on the list
- ```delete 3```: deletes 3rd task on the list
- ```delete 1```: delete 1st task on the list

Example Output: 
```
Blob has removed the task below! 
    [E][ ] birthday celebration (from: 3 Apr 1800 to: 3 Apr 2200)
Now you have 2 tasks left!
```

## 6. Mark tasks 
***
To mark specific tasks with "X", you can use the ```mark``` command followed by the task number, which is based on input sequence.\
Format: ```mark [task number]```\
Examples:
- ```mark 2```: mark 2nd task on the list
- ```mark 3```: mark 3rd task on the list
- ```mark 1```: mark 1st task on the list

Example Output: 
```
Nice! I've marked this task as done: 
    [T][X] study for exam
```

## 7. Unmark tasks
***
To unmark specific tasks, you can use the ```unmark``` command followed by the task number, which is based on input sequence.\
Format: ```unmark [task number]```\
Examples:
- ```unmark 2```: unmark 2nd task on the list
- ```unmark 3```: unmark 3rd task on the list
- ```unmark 1```: unmark 1st task on the list

Example Output:
```
OK, I've marked this task as not done yet: 
    [T][] study for exam
```

## 8. Display entire list of existing tasks 
***
To display the entire list of existing tasks, you can use the ```list``` command.\
Format: ```list```

## 9. Display list of deadlines based on date 
***
To display the list of deadlines based on date, you can use the ```deadlineslist``` command followed by the specific due date in the format yyyy/MM/dd.\
Format: ```deadlineslist [due date in required format]```

Examples: 
- ```deadlineslist 2025/03/03```
- ```deadlineslist 2025/05/25```



