import SmartToyIcon from "@mui/icons-material/SmartToy";
import Avatar, { AvatarProps } from "@mui/joy/Avatar";

type AvatarWithStatusProps = AvatarProps & {
  online?: boolean;
};

export default function AvatarWithStatus(props: AvatarWithStatusProps) {
  return (
    <Avatar size="sm" {...props} color="primary">
      <SmartToyIcon />
    </Avatar>
  );
}
