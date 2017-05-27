# Welcome to the Arena Project.

The entire purpose of this project is to teach programming principles. This project will evolve
to show why functions and Object Oriented Programming principles are useful. Many times, 
students are given small programs that use OOP principles, but the program lacks the size and 
complexity to really show the advantages of such things. This program (and the repository that
contains it) is designed to provide a learner with a large enough playground to understand WHY
OOP principles are actually useful and not just unnecessary and confusing.

## THIS VERSION

**_Form:_** 
This version uses no functions and as few objects as reasonably possible. (Not using char[]
instead of Strings, that's just cruel.) The program is completely written in main() function
of the Arena class in the default package.

This serves as a baseline verison of the game before later versions begin refactoring the code to
use function, objects, etc.

**_Function:_**
Console-based game where the user selects the size of the arena and how many gladiators will be
fighting in that arena. The user can customize their gladiators if they want. If no customization
is requested, gladiators are auto-generated.

The battlefields is shown on the left and the gladiator stats are listed on the right. The numbers
on the battlefield grid show where the gladiator with the corresponding number is located. Items 
are denoted by letters. Each turn in the game is triggered by the user pressing any key. Gladiators
will move randomly one space in any direction or not move at all.

Gladiators have the following stats: HP, Attack, Defense, Speed, and Energy. HP denotes how much
damage they can take before falling unconscious. Attack is the base stat added to attack damage
rolls. Defense is the base stat added to damage reduction rolls. Speed is the base stat added to
a speed roll to determine who attacks first and who gets an item first (more on that later). Energy
is depleted one per turn. When a gladiators energy reaches 0, they fall unconscious.

Items boost a particular stat by 20 points. The Armor increases HP, the Sword increases Attack,
the Shield increases Defense, the Chocobo Feather increases Speed, and the Red Bull increases
Energy.

When a gladiator moves into the same square as an item, they pick it up. If multiple gladiators 
move into a square with an item on the same turn, a speed roll determines who makes it to the item
first. When gladiators move into the same square as either other, they fight each other with the
fastest fighter dealing damage first. When a gladiator is knocked out, they drop all the items
they were holding, making them available to the other gladiators again.

The order of events during a turn is : Gladiators move, then pickup items, then fight, then 
energy is depleted by one. If at this point the fighters energy drops to 0, they fall unconscious.
Turns continue until only one (or fewer the last gladiators fall at the same time) gladiator remains.
