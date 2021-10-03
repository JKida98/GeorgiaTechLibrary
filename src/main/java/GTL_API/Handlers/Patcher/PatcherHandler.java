package GTL_API.Handlers.Patcher;

import org.springframework.stereotype.Component;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.Objects;

@Component
public class PatcherHandler {

    public <T> void fillNotNullFields(T reference, T source) throws IntrospectionException {
        try {
            Arrays.asList(Introspector.getBeanInfo(reference.getClass(), Object.class)
                    .getPropertyDescriptors())
                    .stream()
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .forEach(pd -> {
                        try {
                            Object value = pd.getReadMethod().invoke(source);
                            if (value != null)
                                pd.getWriteMethod().invoke(reference, value);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IntrospectionException e) {
            throw e;
        }
    }
}