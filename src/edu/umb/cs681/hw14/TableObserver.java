package edu.umb.cs681.hw14;

public class TableObserver implements Observer<StockEvent> {

	private StockEvent s_event;

	public StockEvent getS() {
		return s_event;
	}

	@Override
	public void update(Observable sender, StockEvent stockEvent) {
		if (sender instanceof StockQuoteObservable && stockEvent instanceof StockEvent) {

			StockEvent new_t = new StockEvent(stockEvent.ticker(), stockEvent.quote());
			
			this.s_event = new_t;
			System.out.println("TableObserver - Stock updated: " + stockEvent.ticker() + " to " + stockEvent.quote());
		}
	}
}
