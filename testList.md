AlphaStone Test

- **OK** Given an initialized game, Findus is player in turn
- **OK** When Findus ends its turn, it is Peddersen in turn
- **OK** Findus should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2
- **OK** Card Dos has attributes (2,2,2)
- **OK** When Findus plays Uno, Then it is allowed (Status.OK), and Then minion Uno appears at index 0 on the field
- **OK** When Peddersen plays Dos, Then the mana available is two less
- **OK** When Findus plays a card, Then Peddersen still has 3 cards in his hand
- etc.
- **OK** Findus should have four cards when drawing 1 cards.
- **OK** Turn number should increase after the end of each turn
- **OK** When Findus plays Dos, Then the mana available is two less.
- **OK** Cards are removed when drawn
- **OK** Card in hand works for both Findus and Pedderson
- **OK** After each turn 3 mana is added
- **OK** when power is used 2 mana is used
- **OK** Only able to use Power once a round - Gusatv
- **OK** Hero should be active at the start of each players turn - Eske
- **OK** Not able to use card or attack when mana is insuficient
- **OK** it's not possible to use a card when it's not your turn
- **OK** it's not possible to use card from opponents hand
- **OK** Only able to use Power once a round
- **OK** Hero should be active at the start of each players turn
- **OK** That first card drawn in round 2 is cuatro
- **OK** Findus wins after 4 rounds at turn 8
- **OK** When cards are drawn it's not active
- **OK** After one round on the field the card is active
- **OK** Field size is 2 when 2 cards is played
- **OK** When hero power is used 0 damage is taken by opponent.
- **OK** Minion deals damage to opponents card.
- **OK** It's not possible to attack with an inactive minion
- **OK** It's not possible to attack own minion (Vi har ikke brugt getcardsinfield for denne løsning)
- **OK** Only possible to attack when it's the players turn
- **OK** if minion has 0 health then it should be removed from field
- **OK** If minion attacks hero, hero loses health
- **OK** Minion cannot attack when it's not active
- **OK** Minion cannot attack when it is not its turn
- **OK** when new round starts mana is set to 3
- **OK** attacker loses life when attacking.
- **OK** not able to attack with opponents card
- **OK** gethand() should return uno dos tres in the begining
- **OK** Minion is only able to attack once per round
- **OK** GetField() should return an iterable of findus field
- **OK** GetHand().hasNext metoden should return true if there is cards in the hand false otherwise
- **OK** player should lose 2 health if there is no cards to pick from
- **OK** Player fnidus should be of type "BABY"

BetaStone Tests

- **OK** In round 2 the mana should be 2
- **OK** in round 10 the mana amount in the beginning of the round is 7
- **OK** If Player Findus hero health is 0 he looses

GammaStone Tests

- **OK** Findus type has the name ThaiChef
- **OK** ThaiChef uses chilli power and Pedersens heros health decreases by 2
- **OK** When hero power sovs is used there is a minion on the field with (attack, health) = (1,1).

DeltaStone Tests

- **OK** Each turn your mana is set to 7
- **OK** The decksize is 21 after dealing 3 cards
- **OK** the first card needs to cost a maximum of 1 mana
- **OK** the second card needs to cost a maximum of 2 mana
- **OK** the Third card needs to cost a maximum of 4 mana

EpsilonStone Tests

- **OK** Findus heroType is french chef
- **OK** When redwine power is used a minion looses 2 health 
- **OK** When redwine power is used minion on index 1 looses 2 health
- **OK** When pasta is used a random minion on Peddersens field increases attack by 2.
- **OK** When pasta is used minion on Peddersens field on index 1 increases attack by 2
- **OK** Findus wins after dealing 7 damage from minion to minion

ZetaStone Test
- **OK** Deck consist of 7 cinco cards
- **OK** Findus should win if peddersons hero has 0 health in round 3
- **OK** Findus should win with an attack output of 20 in round 10
- **OK** Does not win if attackoutput is 20 but round is 5

EtaStone Test
- **OK** When Brown Rice card is used, 1 damage is dealt to the opponents hero
- **OK** When Tomato Salad is used it adds a +1 attack to own random minion 
- **OK** Tomato salad effect does nothing if no minion is on the field
- **OK** When poke bowl card is used, 2 health is added to own hero
- **OK** when noodleSoup is used, a card is drawn
- **OK** When Chicken curry is used a random opponent minion is killed
- **OK** When Beef Burger is played Add +2 attack to random opponent minion; 


SemiStone Test
- Pedderson Can get thaiChef as hero type (Test stub). 
- if Pedderson have thaiChef, his power is accordingly(Test stub). 


Fjern Cast i executeAttack?
også i setCardsOnFieldActive

Jeff