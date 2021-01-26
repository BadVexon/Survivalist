package theSurvivalist;

import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;

public class CardFilter implements ClassFilter
{
    private static final String PACKAGE = "theSurvivalist.cards";

    @Override
    public boolean accept(ClassInfo classInfo, ClassFinder classFinder)
    {
        return classInfo.getClassName().startsWith(PACKAGE);
    }
}