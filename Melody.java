import java.util.*;

public class Melody {
    private Queue <Note> notes;
    public Melody (Queue<Note> song)
    {
        notes = new LinkedList<>();
        while (!song.isEmpty())
        {
            notes.offer(song.poll());
        }
    }
    public double getTotalDuration ()
    {
        double count = 0;
        Note temp = null;
        for (int i = 0; i<notes.size(); i++)
        {
            temp = notes.poll();
            if (temp.isRepeat())
            {
                count+= temp.getDuration()*2;
                notes.offer(temp);
                while (!notes.peek().isRepeat())
                {

                    temp = notes.poll();
                    count+= temp.getDuration()*2;
                    notes.offer(temp);
                    i++;
                }
                temp = notes.poll();
                count+= temp.getDuration()*2;
                notes.offer(temp);
                i++;
            }
            else
            {
                count+= temp.getDuration();
                notes.offer(temp);
            }
        }
        return count;
    }
    public String toString ()
    {
        String str = "";
        Note temp = null;
        for (int i = 0; i<notes.size(); i++)
        {
            temp = notes.poll();
            str += temp.toString() + "\n";
            notes.offer(temp);
        }
        return str;
    }
    public void changeTempo (double tempo)
    {
        Note temp = null;
        for (int i = 0; i<notes.size(); i++)
        {
            temp = notes.poll();
            temp.setDuration(temp.getDuration()*(1/tempo));
            notes.offer(temp);
        }
    }
    public void reverse ()
    {
        Stack<Note> backwards = new Stack<>();
        while (!notes.isEmpty())
        {
            backwards.push(notes.poll());
        }
        while (!backwards.isEmpty())
        {
            notes.offer(backwards.pop());
        }
    }
    public void append (Melody other)
    {
        Note temp = null;
        for (int i = 0; i<other.notes.size(); i++)
        {
            temp = other.notes.poll();
            this.notes.offer(temp);
            other.notes.offer(temp);
        }
    }
    public void play()
    {
        Queue <Note> repeated = new LinkedList<>();
        Note temp = null;
        int size = notes.size();
        for (int i = 0; i<size+1; i++)
        {
            if (notes.peek().isRepeat())
            {
                repeated.offer(notes.poll());
                repeated.peek().play();
                while (!notes.peek().isRepeat())
                {
                    temp = notes.poll();
                    repeated.offer(temp);
                    temp.play();
                }
                temp = notes.poll();
                temp.play();
                repeated.offer(temp);
                while (!repeated.isEmpty())
                {
                    temp = repeated.poll();
                    temp.play();
                    notes.offer(temp);
                    i++;
                }

            }
            else {

                temp = notes.poll();
                temp.play();
                notes.offer(temp);
            }
        }
    }
    public Queue<Note> getNotes ()
    {
        return notes;
    }
}