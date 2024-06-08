    package models;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;
    import lombok.Getter;
    import lombok.Setter;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public class Raca {
        private Long id;
        private String nomeRaca;
    }
