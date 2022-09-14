- **OK** Given an initialized game, Findus is player in turn
- **OK** When Findus ends its turn, it is Peddersen in turn
- **OK** Findus should have three cards in hand, Tres at index 0, Dos at index 1, and Uno at index 2
- **OK** Card Dos has attributes (2,2,2)
- **OK** When Findus plays Uno, Then it is allowed (Status.OK), and Then minion Uno appears at index 0 on the field
- **OK** When Peddersen plays Dos, Then the mana available is two less
- **OK** When Findus plays a card, Then Peddersen still has 3 cards in his hand
- etc.

New ones
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
- **OK** gethand should return uno dos tres in the begining
- **OK** Minion is only able to attack once per round 


Make a testcase where a game is started and 1 card from each player is played, and then called in all the test












