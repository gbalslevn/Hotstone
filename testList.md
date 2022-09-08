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
- **OK** when new round starts mana is set to 3 
- **OK** After each turn 3 mana is added
- **OK** when power is used 2 mana is used
- **OK** Only able to use Power once a round - Gusatv
- **OK** Hero should be active at the start of each players turn - Eske
- **OK** Not able to use card or attack when mana is insuficient
- **OK** it's not possible to use a card when it's not your turn
- **OK** it's not possible to use card from opponents hand
- **OK** Only able to use Power once a round
- **OK** Hero should be active at the start of each players turn
- That first card drawn in round 2 is cuatro
- Findus wins after 4 rounds at turn 8
- When cards are drawn its not active
- When hero attack is used 0 damage is taken by opponent.
- it's not possible to attack with an inactive minion
- it's not possible to attack own minion



Backlog:
Winning. Player Findus wins after 4 rounds (that is, each of the two players have
4 turns completed, after which Findus is winner at the start of turn number 8).
-	Fieldsize metoden
-	getField metoden
-	getWinner metoden
-	attackCard metoden
-	attackHero metoden
-	is the card active on the field? – We need to fix it



















