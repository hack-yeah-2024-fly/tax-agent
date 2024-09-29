import AddIcon from "@mui/icons-material/Add";
import Button from "@mui/joy/Button";
import Stack from "@mui/joy/Stack";
import Typography from "@mui/joy/Typography";

type AdvisorHeaderProps = {
  onNewQuery: () => void;
};

export const AdvisorHeader: React.FC<AdvisorHeaderProps> = ({ onNewQuery }) => {
  return (
    <Stack
      direction="row"
      sx={{
        justifyContent: "space-between",
        py: { xs: 2, md: 2 },
        px: { xs: 1, md: 2 },
        borderBottom: "1px solid",
        borderColor: "divider",
        backgroundColor: "background.body",
      }}
    >
      <Stack
        direction="row"
        spacing={{ xs: 1, md: 2 }}
        sx={{ alignItems: "center" }}
      >
        <div>
          <Typography
            component="h2"
            noWrap
            sx={{ fontWeight: "lg", fontSize: "lg" }}
          >
            Asystent: Deklaracje i zeznania PCC
          </Typography>
          <Typography level="body-sm">
            Zapytaj naszego agenta o uzupelnienie formularza PCC-3
          </Typography>
        </div>
      </Stack>
      <Stack spacing={1} direction="row" sx={{ alignItems: "center" }}>
        <Button
          startDecorator={<AddIcon />}
          color="neutral"
          variant="outlined"
          size="sm"
          sx={{ display: { xs: "none", md: "inline-flex" } }}
          onClick={onNewQuery}
        >
          Nowe zapytanie
        </Button>
      </Stack>
    </Stack>
  );
};
