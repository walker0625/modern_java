package stream;

import java.util.Optional;

public class Classes {

    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    // 필드 자체에 Optional을 쓰는 것도 비권장되는 사항 > 차라리 상위/하위 클래스를 생성하는 것이 맞음
    //private Optional<Progress> optionalProgress;

    public Classes(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // return 타입에만 Optional을 쓰는 것이 권장됨!
   public Optional<Progress> getProgress() {
        // null일 수 있는 경우
        return Optional.ofNullable(progress);
    }

    // 정 null을 리턴해야 한다면 return null 대신에 Optional을 return
    // return Optional.empty();
    // parameter에도 사용 가능하나 setProgress(null)과 같은 Code가 가능해짐(위험)
    public void setProgress(Optional<Progress> progress) {
        if(progress != null) {
            progress.ifPresent(p -> this.progress = p);
        }
    }

    // - map의 key에 쓰는 것도 비권장 > map의 key가 null이라는 것 자체가 map에 맞지 않음
    // - Collection, Map, Stream, Array, Optional 등의 Container 성격의 요소들은 Optional로 감싸지 않음
    //   Container들은 그 자체로 null 확인이 가능한데 중복으로 감싸는 꼴이므로
}
