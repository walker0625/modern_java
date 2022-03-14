package optional;

import stream.Classes;
import stream.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalApp {

    public static void main(String[] args) {
        List<Classes> springClasses = new ArrayList<>();
        springClasses.add(new Classes(1, "spring boot", true));
        springClasses.add(new Classes(2, "spring jpa", true));
        springClasses.add(new Classes(3, "spring mvc", false));
        springClasses.add(new Classes(4, "spring core", false));
        springClasses.add(new Classes(5, "rest api", false));

        Optional<Classes> optionalClass = springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring")).findFirst();

        //System.out.println(springClasses.get(1).getProgress().getStudyDuration());

        Optional<Progress> progress = springClasses.get(0).getProgress();

        boolean isClassFinished = progress.orElseGet(OptionalApp::createProgress).isFinished();

        if(progress.isPresent()) {
            System.out.println(progress.get().isFinished());
        }

        progress.ifPresent(p -> System.out.println(p.isFinished()));


        //Progress p = progress.orElseThrow(IllegalStateException::new);
        // Progress는 reference type이라 없을 경우 null을 return, null에서 뭔가를 꺼내려하니
        // nullPointerException(런타임 에러라 컴파일 시에 감지도 안됨) 발생함
        // System.out.println(springClasses.get(1).getProgress().getStudyDuration());

        // 1. 이를 막기위해 null 체크를 하지만 놓치는 경우가 생김
        if(progress != null) {
            //System.out.println(progress.getStudyDuration());
        }

        // 2. 아예 getProgress()를 할때 exception을 던지는 방법도 있으나
        // 에러를 던지는 것은 stack trace를 찍게됨으로 그만큼의 리소스를 소비하는 것
        // 이러한 비어있는 값을 return 할 수 있는 경우를 대비하여 Optional이라는 기능이 생김
        /*public Progress getProgress() {
            if(this.progress == null) {
                throw new IllegalStateException();
            }
            return progress;
        }*/

        // Primitive Type을 Optional로 감쌀 때는 해당하는 메소드를 사용하는 것이 
        // boxing에 쓰이는 리소스를 아낌
        Optional.of(10);
        OptionalInt.of(10);
    }

    private static Progress createProgress(){
        return new Progress(true);
    }

}
