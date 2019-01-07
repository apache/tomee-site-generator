# xbean-finder

## AnnotationFinder

It uses ASM create an index of annotations and classes in a specific archive. Reason for using ASM are:

  - Security: Loading classes involves executing static initializers.  Imagine doing this for every class in every jar.
  - Speed: Loading all those classes is slow
  - Memory: Chews up permgen space quickly and needlessly.  Additional note, see above, some static initializers may hook themselves into the system and make the entire classloader (and the thousands of classes loaded) impossible to GC.

### Usage

Say you had an `@Plugin` annotation you used, you could do as follows and skip the
whole `META-INF` business:

    AnnotationFinder finder = new AnnotationFinder(new JarArchive(classloader, jarUrl));
    List<Class<?>> plugins = finder.findAnnotatedClasses(Plugin.class);

That's the basics.

    public class AnnotationFinder {
        boolean isAnnotationPresent(Class<? extends Annotation> annotation);

        List<String> getClassesNotLoaded();

        List<Package> findAnnotatedPackages(Class<? extends Annotation> annotation);

        List<Class<?>> findAnnotatedClasses(Class<? extends Annotation> annotation);

        List<Class<?>> findInheritedAnnotatedClasses(Class<? extends Annotation> annotation);

        List<Method> findAnnotatedMethods(Class<? extends Annotation> annotation);

        List<Constructor> findAnnotatedConstructors(Class<? extends Annotation> annotation);

        List<Field> findAnnotatedFields(Class<? extends Annotation> annotation);

        List<Class<?>> findClassesInPackage(String packageName, boolean recursive);

        <T> List<Class<? extends T>> findSubclasses(Class<T> clazz);

        <T> List<Class<? extends T>> findImplementations(Class<T> clazz);

        List<Annotated<Method>> findMetaAnnotatedMethods(Class<? extends Annotation> annotation);

        List<Annotated<Field>> findMetaAnnotatedFields(Class<? extends Annotation> annotation);

        List<Annotated<Class<?>>> findMetaAnnotatedClasses(Class<? extends Annotation> annotation);

        List<String> getAnnotatedClassNames();
    }

## Archive

So what we have now is a composable system.  You create your finder and feed it an archive, like so:

    Archive archive = new JarArchive(classloader, jarURL);
    AnnotationFinder finder = new AnnotationFinder( archive );
    List<Class<?>> plugins = finder.findAnnotatedClasses(PluginAnnotation.class)

If you want some filtering, you add that in:

    Archive archive = new JarArchive(classloader, jarURL);

    archive = new FilteredArchive(archive, new Filter {

        @Override
        public boolean accept(String name) {
            return name.startsWith("org.foo.");
        }
    });

    AnnotationFinder finder = new AnnotationFinder( archive );
    List<Class<?>> plugins = finder.findAnnotatedClasses(PluginAnnotation.class)

Several archives can be composed together via `CompositeArchive`

    Archive archive = new CompositeArchive(
        new JarArchive(classloader, jarURL),
        new FileArchive(classloader, new File("target/classes/")),
        new ClassesArchive(Foo.class, Bar.class)
        );

Sky is the limit.

We have the following `Archive` implementations

 - ClassesArchive(Class<?>... classes)
 - ClassesArchive(Iterable<Class<?>> classes)
 - FileArchive(ClassLoader loader, URL url)
 - FileArchive(ClassLoader loader, File dir)
 - JarArchive(ClassLoader loader, URL url)

For creating combinations of the above we have:

 - CompositeArchive(Archive... archives)
 - CompositeArchive(Iterable<Archive> archives)

For filtering classes out of archvies:

 - FilteredArchive(Archive archive, Filter filter)

And a convenience class to quickly get an Archive from a set of urls

 - ClasspathArchive(ClassLoader loader, URL... urls)
 - ClasspathArchive(ClassLoader loader, Iterable<URL> urls)

The above currently only supports `jar:` and `file:` urls

## Filters

Several built in filters exist for convenience

 - ClassFilter(String name)
 - ContainsFilter(String token)
 - PackageFilter(String packageName)
 - PatternFilter(String expression)
 - PatternFilter(Pattern pattern)
 - PrefixFilter(String prefix)
 - SuffixFilter(String suffix)


As well as some filter implementations that allow all of the above to be composed together

 - ExcludeIncludeFilter(Filter include, Filter exclude)
 - FilterList(Filter... filters)
 - FilterList(Iterable<Filter> filters)
 - IncludeExcludeFilter(Filter include, Filter exclude)

And the following convenience class for quickly creating any of the above

    public class Filters {
        public static Filter packages(String... packages) {
        public static Filter classes(String... classes) {
        public static Filter prefixes(String... prefixes) {
        public static Filter tokens(String... tokens) {
        public static Filter suffixes(String... suffixes) {
        public static Filter patterns(String... patterns) {
        public static Filter optimize(Filter... filters) {
        public static Filter optimize(List<Filter>... filterss) {
        public static Filter invert(Filter filter) {
    }


## ResourceFinder

Something similar to Java 6 ServiceLoader, except doesn't do the instantiations, but you could add that for yourself very easily.

Using the same `META-INF` layout and files as you posted, you can do like:

    ResourceFinder finder = new ResourceFinder("META-INF/services/");
    List plugins = finder.findAllImplementations(Plugin.class);

A little neater if you adjusted your META-INF layout as follows

    META-INF/com.example.plugins.Plugins/red
    META-INF/com.example.plugins.Plugins/blue

...where the "red" file contained the text "com.example.plugins.RedPlugin" and the
"blue" file contained the text "com.example.plugins.BluePlugin", you could then get them
in a map like so:

    Map plugins = finder.mapAvailableImplementations(Plugin.class);
    Class red = plugins.get("red");
    Class blue = plugins.get("blue");

Now say you want to do something similar, but the "red" and "blue" files are
properties files which contain the name of the implementation class and other
configurable properties for your red and blue plugins.

    ResourceFinder finder = new ResourceFinder("META-INF/services/");
    Map pluginConfigs = finder.mapAllProperties(Plugin.class.getName());
    Properties redConfig = pluginConfigs.get("red");
    Properties blueConfig = pluginConfigs.get("blue");

Object instantiation was never written into any of those libraries because we're big fans
of xbean-reflect package, which is a real "don't tell me what to do" library for when
you just want to create a simple object and would like to get real basic
field/setter/constructor injection without choking down a whole "i control everything"
framework.

You just:

    ObjectRecipe recpie = new ObjectRecipe("com.example.plugins.RedPlugin");
    recpie.setProperty("myDateField","2008-04-17"); recpie.setProperty("myIntField","100");
    recpie.setProperty("myBooleanField","true");
    recpie.setProperty("myUrlField","http://www.theserverside.com");
    Plugin red = (Plugin) recpie.create();
    red.start();

Obviously, the above style to object creation couples really well to the `ResourceFinder` method
that gives you Properties objects back. You put the class name and config for your plugin in the
properties files and pass the properties right into the ObjectRecipe and more or less get a
little do-it-yourself IoC plugin system.

# OpenEJB/TomEE

Here is a grep of some of the calls made to `AnnotationFinder`.  Most of this code is in an OpenEJB class called
`AnnotationDeployer` whose primary job is to merge the @annotation and <xml> metadata into one tree.

            for (Annotated<Class<?>> clazz : finder.findMetaAnnotatedClasses(LocalClient.class)) {
            for (Annotated<Class<?>> clazz : finder.findMetaAnnotatedClasses(RemoteClient.class)) {
        	List<Class<?>> connectorClasses = finder.findAnnotatedClasses(Connector.class);
        	List<Class<?>> classes = finder.findAnnotatedClasses(ConnectionDefinitions.class);
        	classes = finder.findAnnotatedClasses(ConnectionDefinition.class);
        	classes = finder.findAnnotatedClasses(Activation.class);
        	classes = finder.findAnnotatedClasses(AdministeredObject.class);
            classes.addAll(finder.findAnnotatedClasses(WebService.class));
            classes.addAll(finder.findAnnotatedClasses(WebServiceProvider.class));
            for (Annotated<Class<?>> beanClass : finder.findMetaAnnotatedClasses(Singleton.class)) {
            for (Annotated<Class<?>> beanClass : finder.findMetaAnnotatedClasses(Stateless.class)) {
            for (Annotated<Class<?>> beanClass : finder.findMetaAnnotatedClasses(Stateful.class)) {
            for (Annotated<Class<?>> beanClass : finder.findMetaAnnotatedClasses(ManagedBean.class)) {
            for (Annotated<Class<?>> beanClass : finder.findMetaAnnotatedClasses(MessageDriven.class)) {
            List<Class<?>> appExceptions = finder.findAnnotatedClasses(ApplicationException.class);
                    List<Class<?>> list = finder.findAnnotatedClasses(annotation);
                final List<Annotated<Class<?>>> annotatedClasses = sortClasses(annotationFinder.findMetaAnnotatedClasses(Interceptors.class));
                final List<Annotated<Method>> annotatedMethods = sortMethods(annotationFinder.findMetaAnnotatedMethods(Interceptors.class));
                for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(ExcludeDefaultInterceptors.class)) {
                for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(ExcludeClassInterceptors.class))) {
                            if (annotationFinder.isAnnotationPresent(Path.class) || !annotationFinder.findAnnotatedMethods(Path.class).isEmpty()) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(Asynchronous.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(Asynchronous.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(RolesAllowed.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(PermitAll.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(DenyAll.class)) {
            scheduleMethods.addAll(annotationFinder.findMetaAnnotatedMethods(javax.ejb.Schedules.class));
            scheduleMethods.addAll(annotationFinder.findMetaAnnotatedMethods(javax.ejb.Schedule.class));
                for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(PostConstruct.class))) {
                for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(PreDestroy.class))) {
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(javax.interceptor.AroundInvoke.class))) {
                    for (Annotated<Method> method : sortMethods((annotationFinder.findMetaAnnotatedMethods(javax.interceptor.AroundTimeout.class)))) {
                    List<Annotated<Method>> timeoutMethods = sortMethods(annotationFinder.findMetaAnnotatedMethods(javax.ejb.Timeout.class));
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(AfterBegin.class))) {
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(BeforeCompletion.class))) {
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(AfterCompletion.class))) {
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(PostActivate.class))) {
                    for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(PrePassivate.class))) {
                for (Annotated<Method> method : sortMethods(annotationFinder.findMetaAnnotatedMethods(Init.class))) {
                List<Annotated<Method>> removeMethods = sortMethods(annotationFinder.findMetaAnnotatedMethods(Remove.class));
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(EJBs.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(EJB.class)) {
            for (Annotated<Field> field : annotationFinder.findMetaAnnotatedFields(EJB.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(EJB.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(Resources.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(Resource.class)) {
            for (Annotated<Field> field : annotationFinder.findMetaAnnotatedFields(Resource.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(Resource.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(WebServiceRefs.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(WebServiceRef.class)) {
            for (Annotated<Field> field : annotationFinder.findMetaAnnotatedFields(WebServiceRef.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(WebServiceRef.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(PersistenceUnits.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(PersistenceUnit.class)) {
            for (Annotated<Field> field : annotationFinder.findMetaAnnotatedFields(PersistenceUnit.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(PersistenceUnit.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(PersistenceContexts.class)) {
            for (Annotated<Class<?>> clazz : annotationFinder.findMetaAnnotatedClasses(PersistenceContext.class)) {
            for (Annotated<Field> field : annotationFinder.findMetaAnnotatedFields(PersistenceContext.class)) {
            for (Annotated<Method> method : annotationFinder.findMetaAnnotatedMethods(PersistenceContext.class)) {
            int ann = annotationFinder.findAnnotatedClasses(handler.getAnnotationClass()).size();
            ann += annotationFinder.findAnnotatedMethods(handler.getAnnotationClass()).size();
            List<Annotated<Class<?>>> types = sortClasses(annotationFinder.findMetaAnnotatedClasses(annotationClass));
            List<Annotated<Method>> methods = annotationFinder.findMetaAnnotatedMethods(annotationClass);
        List<Class<?>> annotatedClasses = finder.findAnnotatedClasses(Path.class);
        methods.addAll(finder.findAnnotatedMethods(Path.class));
