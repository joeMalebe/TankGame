package List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ListIterator<T> implements Iterator<IPosition<T>> {
	int size;
	IPosition<T> cursor;
	List<T> data;
	
	@SuppressWarnings("unchecked")
	public ListIterator(List<T> data)
	{
		size = data.size();
		cursor =  data.first();
		this.data = data;
	}
	@Override
	public boolean hasNext() {
		try{
			if(!data.isEmpty() && data.after(cursor) != null)
				return true;
				else 
					return false;
		}catch(IllegalArgumentException ex)
		{
			return false;
		}
		
	}

	@Override
	public IPosition<T> next() throws NoSuchElementException {
		if(this.hasNext())
		{
			IPosition<T> temp = cursor;
			cursor = data.after(cursor);
			return temp;
		}
		else throw new NoSuchElementException("No next element");
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	

}
